package magic_pot.magic_pot;

import magic_pot.blocks.Modblocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTbale {
    public static final DeferredRegister<CreativeModeTab>CREATIVE_MODE_TAB=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB,Magic_pot.MODID);

    public static final RegistryObject<CreativeModeTab> MP_TAB=
            CREATIVE_MODE_TAB.register("mp_tab",()-> CreativeModeTab.builder()
                    .icon(()->new ItemStack(ModItem.ABAND_SHELl.get()))
                    .title(Component.translatable("Magic_pot"))
                    .displayItems((pParameters,pOutput)->{
                        //mod的物品获取，在物品栏中嗯对




                        pOutput.accept(ModItem.ABAND_SHELl.get());
                        pOutput.accept(ModItem.MARINE_QJ.get());
                        pOutput.accept(ModItem.DISC_HELL.get());
                        pOutput.accept(ModItem.HEART_HELL.get());
                        pOutput.accept(ModItem.HEART_END.get());
                        pOutput.accept(ModItem.BOW_PURPLE.get());
                        pOutput.accept(ModItem.BOW_GREEN.get());
                        pOutput.accept(ModItem.BOW_RED.get());
                        pOutput.accept(Modblocks.FIRE_FLOWER.get());







                        pOutput.accept(Modpotion.SULFUR_POT.get());
                        pOutput.accept(Modpotion.AIR_JUMP.get());
                        pOutput.accept(Modpotion.CLIMB.get());
                        pOutput.accept(ModItem.SULFUR.get());
                        pOutput.accept(Modpotion.SEA_SPEED.get());
                        pOutput.accept(Modpotion.WATER.get());
                        pOutput.accept(Modpotion.LOVE_U_POT.get());
                        pOutput.accept(Modpotion.LOSE_WAY_POT.get());
                        pOutput.accept(Modpotion.IRON_BODY_POT.get());
                        pOutput.accept(Modpotion.EMPTY.get());
                        pOutput.accept(Modpotion.DIME_TELE_POT.get());
                        pOutput.accept(Modpotion.SULFUR_POT_SPRAY.get());
                        pOutput.accept(Modpotion.DIME_TELE_POT_SPRAY.get());
                        pOutput.accept(Modpotion.SEA_SPEED_POT_SPRAY.get());










                    }).build());
    public static void register(IEventBus eventBus){ CREATIVE_MODE_TAB.register(eventBus);
                    }
}
