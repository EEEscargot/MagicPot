package magic_pot.magic_pot;

import magic_pot.potions_effect.*;
import magic_pot.potions_ues.Lose_way_pot;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
//效果的颜色会在这里注册完成，在potion_effect写药水效果
//注意不要“”内大写字母
public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "magic_pot");

    public static final RegistryObject<MobEffect> MARINE_QJ_EFFECT = MOB_EFFECTS.register("marine_qj_effect",
            () -> new Marine_qj_effect(MobEffectCategory.BENEFICIAL, 0x98D982));

    public static final RegistryObject<MobEffect> SULFUR_EFFECT = MOB_EFFECTS.register("sulfur_effect",
            () -> new Sulfur_effect(MobEffectCategory.NEUTRAL, 0xCCAA00));

    public static final RegistryObject<MobEffect> DIME_TELE_EFFECT = MOB_EFFECTS.register("dime_tele_effect",
            () -> new Dime_tele_effect(MobEffectCategory.NEUTRAL, 0x800080));

    public static final RegistryObject<MobEffect> LOVE_U_EFFECT = MOB_EFFECTS.register("love_u_effect",
            () -> new Love_u_effect(MobEffectCategory.NEUTRAL, 0xFF1493));

    public static final RegistryObject<MobEffect> LOSE_WAY_EFFECT = MOB_EFFECTS.register("lose_way_effect",
            () -> new Lose_way_effect(MobEffectCategory.NEUTRAL, 0xFF1493));

    public ModEffects(MobEffectCategory category, int color) {
    }

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}