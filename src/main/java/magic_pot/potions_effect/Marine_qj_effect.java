package magic_pot.potions_effect;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeMod;


public class Marine_qj_effect extends MobEffect {

    public Marine_qj_effect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "91AEAA56-376B-4498-935B-2F7F68070635",
                0.40D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                6.0D,
                AttributeModifier.Operation.ADDITION
        );
        this.addAttributeModifier(
                ForgeMod.SWIM_SPEED.get(),
                "173e81fb-03fe-4e24-9650-f4ea529dcaee",
                2,
                AttributeModifier.Operation.ADDITION
        );
        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "7a375f04-745c-4a94-8ecc-8e4e932ff577",
                1,
                AttributeModifier.Operation.ADDITION
        );
        //此处应该还有挖掘速度，但是1.20.1中没有Block_break的参数,改到事件监听写
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()) {
                pLivingEntity.heal(6.0F + pAmplifier);
            }
        }

        // 判断实体是否在水里
        if (pLivingEntity.isInWater()) {

            pLivingEntity.addEffect(new MobEffectInstance(
                    MobEffects.WATER_BREATHING, 220, 0, false, false, false
            ));
            pLivingEntity.addEffect(new MobEffectInstance(
                    MobEffects.NIGHT_VISION, 220, 0, false, false, false
            ));

        }
        if (!(pLivingEntity instanceof Player player)) {
            return;
        }

        if (player.level().isClientSide()) {
            return;
        }

        final String WAS_IN_WATER_KEY = "WasInWater";

        CompoundTag data = player.getPersistentData();

        boolean wasInWater = data.getBoolean(WAS_IN_WATER_KEY);
        boolean nowInWater = player.isInWater() || player.isUnderWater();

//进入水中时触发
        if (!wasInWater && nowInWater) {
            ItemStack fish = new ItemStack(Items.COD);

            if (!player.getInventory().add(fish)) {
                player.spawnAtLocation(fish);
            }
        }

//更新状态
        data.putBoolean(WAS_IN_WATER_KEY, nowInWater);
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
            return pDuration % 10 == 0;
    }
}
