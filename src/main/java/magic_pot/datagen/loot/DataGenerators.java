package magic_pot.datagen.loot;

import magic_pot.magic_pot.Magic_pot;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Magic_pot.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generators= event.getGenerator();
        PackOutput packOutput=generators.getPackOutput();

        generators.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(packOutput));
    }
}
