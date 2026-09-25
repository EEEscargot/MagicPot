package magic_pot.magic_pot;

import magic_pot.item.Marine_qj_return;
import magic_pot.potions_ues.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class ModItem {
    public static final DeferredRegister<Item> ITEM =
            DeferredRegister.create(ForgeRegistries.ITEMS, "magic_pot");
    //壳子
    public  static  final RegistryObject<Item>ABAND_SHELl=
            ITEM.register("aband_shell",()->new Item((new Item.Properties().stacksTo(1))));
    //地狱碎片以及地狱之心
    public static final RegistryObject<Item> HEART_HELL=
            ITEM.register("heart_hell",()->new Item((new Item.Properties().stacksTo(1))));
    public static final RegistryObject<Item> DISC_HELL=
            ITEM.register("disc_hell",()->new Item((new Item.Properties().stacksTo(16))));
    //海王琼浆
    public static final RegistryObject<Item> MARINE_QJ =
            ITEM.register("marine_qj", () -> new Marine_qj_return(new Item.Properties()
                    .stacksTo(1)
                    .food(new net.minecraft.world.food.FoodProperties.Builder()
                            .nutrition(10)
                            .saturationMod(0.7f)
                            .alwaysEat()
                            .effect(() -> new net.minecraft.world.effect.MobEffectInstance(ModEffects.MARINE_QJ_EFFECT.get(), 1600, 0), 1.0F)
                            .build()
                    )));
    //硫磺
    public static final RegistryObject<Item> SULFUR=
            ITEM.register("sulfur",()->new Item((new Item.Properties())));
    //末地之心（投掷龙息）
    public static final RegistryObject<Item> HEART_END=
            ITEM.register("heart_end",()->new Item((new Item.Properties().stacksTo(1))));
    //发夹
    public static final RegistryObject<Item> BOW_PURPLE=
            ITEM.register("bow_purple",()->new Item((new Item.Properties().stacksTo(1))));

    public static final RegistryObject<Item> BOW_RED=
            ITEM.register("bow_red",()->new Item((new Item.Properties().stacksTo(1))));

    public static final RegistryObject<Item> BOW_GREEN=
            ITEM.register("bow_green",()->new Item((new Item.Properties().stacksTo(1))));

    public static void register(IEventBus eventBus){
        ITEM.register(eventBus);
    }
}
