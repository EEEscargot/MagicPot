package magic_pot.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import magic_pot.magic_pot.ModEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


@Mod.EventBusSubscriber(modid = "magic_pot")



public class Sulfur_effect_event {
    private static final String FUSE_TAG = "SulfurFuse";


@SubscribeEvent
public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
    Player player = event.getEntity();
    Entity target = event.getTarget();
    Level level = event.getLevel();
    InteractionHand hand = event.getHand();
    ItemStack itemstack = player.getItemInHand(hand);

    //生物
    if (!(target instanceof LivingEntity living)) return;

    //必须效果
    if (!living.hasEffect(ModEffects.SULFUR_EFFECT.get())) return;
    //工具
    if (!itemstack.is(ItemTags.CREEPER_IGNITERS)) return;

    if (living.getHealth() >= 50.0F) {

        float explosionRadius = 3.0F;
        level.explode(living, living.getX(), living.getY(), living.getZ(),
                explosionRadius, Level.ExplosionInteraction.MOB);

        if (!itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }

        level.playSound(null, living.getX(), living.getY(), living.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F, 1.0F);

        living.removeEffect(ModEffects.SULFUR_EFFECT.get());
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.CONSUME);
        return;
    }

    if (level.isClientSide) {
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.SUCCESS);
        return;

    }

    //音效
    SoundEvent soundevent = itemstack.is(Items.FIRE_CHARGE)
            ? SoundEvents.FIRECHARGE_USE
            : SoundEvents.FLINTANDSTEEL_USE;
    level.playSound(null, living.getX(), living.getY(), living.getZ(),
            soundevent, SoundSource.PLAYERS, 1.0F,
            level.random.nextFloat() * 0.4F + 0.8F);

//    float explosionRadius = 3.0F;
//    level.explode(living, living.getX(), living.getY(), living.getZ(),
//            explosionRadius, Level.ExplosionInteraction.MOB);


    CompoundTag tag = living.getPersistentData();

    if (!tag.contains(FUSE_TAG)) {
        tag.putInt(FUSE_TAG, 30); // 默认30 tick = 1.5秒

        // 播放引信嘶嘶声
        level.playSound(null, living.getX(), living.getY(), living.getZ(),
                SoundEvents.CREEPER_PRIMED, SoundSource.HOSTILE, 1.0F, 0.5F);

        // 消耗物品
        if (!itemstack.isDamageableItem()) {
            itemstack.shrink(1);
        } else {
            itemstack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
        }
    }

    // 取消原版交互，防止其他行为
    event.setCanceled(true);
    event.setCancellationResult(InteractionResult.CONSUME);
}

//Tick 事件
@SubscribeEvent
public static void onLivingTick(LivingEvent.LivingTickEvent event) {
    LivingEntity entity = event.getEntity();
    Level level = entity.level();
    if (level.isClientSide) return;

    CompoundTag tag = entity.getPersistentData();
    if (!tag.contains(FUSE_TAG)) return;

    int fuse = tag.getInt(FUSE_TAG);

    if (fuse > 0) {
        if (level.random.nextInt(2) == 0) {
            double d0 = entity.getX() + (level.random.nextDouble() - 0.5) * entity.getBbWidth();
            double d1 = entity.getY() + level.random.nextDouble() * entity.getBbHeight();
            double d2 = entity.getZ() + (level.random.nextDouble() - 0.5) * entity.getBbWidth();
            level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0, 0.0, 0.0);
        }
        tag.putInt(FUSE_TAG, fuse - 1);
        return;
    }

    float explosionRadius = 3.0F;
    level.explode(entity, entity.getX(), entity.getY(), entity.getZ(),
            explosionRadius, Level.ExplosionInteraction.MOB);

    entity.remove(Entity.RemovalReason.KILLED);
    tag.remove(FUSE_TAG);
}
}