package magic_pot.magic_pot;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import oshi.software.os.unix.solaris.SolarisFileSystem;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import static top.theillusivec4.curios.api.CuriosApi.MODID;

@Mod.EventBusSubscriber(modid = "magic_pot",bus=Mod.EventBusSubscriber.Bus.MOD)
public class Modcurios {
    //静态固定栏目
    @SubscribeEvent
    public static void enqueue(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> {
            return SlotTypePreset.NECKLACE.getMessageBuilder()
                    .build();
        });
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,()->{
            return SlotTypePreset.HEAD.getMessageBuilder()
                    .build();
        });
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE,()->{
            return new SlotTypeMessage.Builder("heart")
                    .icon(new ResourceLocation("magic_pot", "slot/heart_slot"))
                    .build();
        });
    }
    //动态栏目，检测玩家是否装有更多与curios联系的mod，如果他们装了总线会创造更多饰品栏
    private void enqueueIMC(final InterModEnqueueEvent event) {
        if(ModList.get().isLoaded("curios")) {
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("earings")
                    .icon(new ResourceLocation(MODID, "slot/earings"))
                    .priority(0)
                    .size(1)
                    .build());
        }
        if(ModList.get().isLoaded("heads")) {
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("heads")
                    .icon(new ResourceLocation(MODID, "slot/heads"))
                    .priority(1)
                    .size(1)
                    .build());
        }
    }
}
