package magic_pot.entity;

import magic_pot.magic_pot.ModEffects;
import magic_pot.magic_pot.ModEntities;
import magic_pot.magic_pot.Modpotion;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import javax.annotation.Nullable;
import java.util.List;


//ai coder
// 呵呵你要问我，欸欸额主播为什么，这个药水能丢这么远啊，我只能说，原版我不会迁移呵呵，，，。


public class Sulfur_pot_spray_enti extends ThrowableItemProjectile {

        public Sulfur_pot_spray_enti(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
            super(pEntityType, pLevel);
        }

        public Sulfur_pot_spray_enti(Level plevel, LivingEntity livingEntity) {
            super(ModEntities.SULFUR_POT_SPRAY_ENTI.get(), livingEntity, plevel);
        }

        @Override
        protected Item getDefaultItem() {
            return Modpotion.SULFUR_POT_SPRAY.get();
        }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        if (!this.level().isClientSide) {
            Entity directHitEntity = (pResult.getType() == HitResult.Type.ENTITY)
                    ? ((EntityHitResult) pResult).getEntity()
                    : null;
            this.level().playSound(
                    null,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    SoundEvents.SPLASH_POTION_BREAK,
                    SoundSource.NEUTRAL,
                    1.0F, // 音量
                    0.8F + this.random.nextFloat() * 0.4F
            );
            this.applyPotionEffects(directHitEntity);
            this.makeParticles();
            this.discard();
        }

    }
//copy原版
    private void applyPotionEffects(@Nullable Entity directHitEntity) {
        AABB aabb = this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, aabb);

        if (!list.isEmpty()) {
            Entity sourceOwner = this.getEffectSource();

            // 效果
            List<MobEffectInstance> customEffects = List.of(
                    new MobEffectInstance(ModEffects.SULFUR_EFFECT.get(),
                            3200,//时间
                            0//等级
                    )
            );

            for (LivingEntity livingentity : list) {
                if (livingentity.isAffectedByPotions()) {
                    double distanceSq = this.distanceToSqr(livingentity);
                    if (distanceSq < 16.0D) { // 4.0 格以内
                        double factor;
                        if (livingentity == directHitEntity) {
                            factor = 1.0D; // 效果 100%
                        } else {
                            factor = 1.0D - Math.sqrt(distanceSq) / 4.0D; // 距离衰减
                        }

                        for (MobEffectInstance mobeffectinstance : customEffects) {
                            MobEffect mobeffect = mobeffectinstance.getEffect();

                            if (mobeffect.isInstantenous()) {
                                mobeffect.applyInstantenousEffect(this, this.getOwner(), livingentity, mobeffectinstance.getAmplifier(), factor);
                            } else {
                                int duration = mobeffectinstance.mapDuration((baseDuration) -> (int) (factor * (double) baseDuration + 0.5D));

                                MobEffectInstance scaledEffect = new MobEffectInstance(
                                        mobeffect,
                                        duration,
                                        mobeffectinstance.getAmplifier(),
                                        mobeffectinstance.isAmbient(),
                                        mobeffectinstance.isVisible()
                                );

                                if (!scaledEffect.endsWithin(20)) {
                                    livingentity.addEffect(scaledEffect, sourceOwner);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private void makeParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            // 生成物品碎裂粒子
            serverLevel.sendParticles(
                    new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                    this.getX(), this.getY(), this.getZ(),
                    20, // 粒子数量
                    0.2, 0.2, 0.15, // X Y Z 方向的随机偏移量
                    0.15 // 粒子飞溅速度
            );
            for (int i = 0; i < 100; i++) {
                double offsetX = (this.random.nextGaussian() * 0.9D);
                double offsetY = (this.random.nextGaussian() * 0.2D);
                double offsetZ = (this.random.nextGaussian() * 0.9D);

                serverLevel.sendParticles(
                        ParticleTypes.ENTITY_EFFECT,
                        this.getX(), this.getY() + 0.5, this.getZ(),
                        0,
                        1.0D, 1.0D, 0.0D,//颜色
                        1.0D
                );
            }
        }
    }
}