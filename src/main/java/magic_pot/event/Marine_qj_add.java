package magic_pot.event;

import magic_pot.magic_pot.ModEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "magic_pot", bus = Mod.EventBusSubscriber.Bus.FORGE)

public class Marine_qj_add {

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();

        if (player.hasEffect(ModEffects.MARINE_QJ_EFFECT.get())) {
            event.setNewSpeed(event.getOriginalSpeed() * 3.0F);
        }
        if (player.isInFluidType(ForgeMod.WATER_TYPE.get())){
            player.heal(1.0f);
        }
    }
}
