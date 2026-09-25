package magic_pot.blocks;

import com.google.common.base.Suppliers;
import magic_pot.magic_pot.ModEffects;
import magic_pot.magic_pot.ModItem;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class Modblocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "magic_pot");

    //方块
    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block) {
        ModItem.ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    //方块物品
    private static <T extends Block> RegistryObject<T> registryBlock(String name, Supplier<T> block) {
        RegistryObject<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }
//写了of(参数在原版代码中)还是能写copy，在后面加上"."以及你的参数就行
//new 后面有几个参数有block，flowerblock，注意。
    public static final RegistryObject<Block> FIRE_FLOWER =
            registryBlock("fire_flower", () -> new FlowerBlock(()-> MobEffects.FIRE_RESISTANCE,5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noCollission().noOcclusion()));


    public static final RegistryObject<Block>POTTED_FIRE_FL=BLOCKS.register("potted_fire_fl",
            ()-> new FlowerPotBlock(()->((FlowerPotBlock)Blocks.FLOWER_POT),Modblocks.FIRE_FLOWER,
            BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
