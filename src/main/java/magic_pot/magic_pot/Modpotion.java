package magic_pot.magic_pot;

import magic_pot.itemUse.Dime_tele_pot_spray;
import magic_pot.itemUse.Sea_speed_pot_spray;
import magic_pot.itemUse.Sulfur_pot_spray;
import magic_pot.potions_ues.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Modpotion {
    public static final DeferredRegister<Item> ITEM =
            DeferredRegister.create(ForgeRegistries.ITEMS, "magic_pot");




    public static final RegistryObject<Item> SEA_SPEED=
            ITEM.register("sea_speed",()->new SeaSpeed(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DIME_TELE_POT=
            ITEM.register("dime_tele_pot",()->new Dime_tele_pot((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item> CLIMB=
            ITEM.register("climb",()->new Item((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item> AIR_JUMP=
            ITEM.register("air_jump",()->new Item((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item> SULFUR_POT=
            ITEM.register("sulfur_pot",()->new Sulfur_Pot((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item>WATER=
            ITEM.register("water",()->new Water((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item>LOVE_U_POT=
            ITEM.register("love_u_pot",()->new Love_u_pot((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item>LOSE_WAY_POT=
            ITEM.register("lose_way_pot",()->new Lose_way_pot((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item>IRON_BODY_POT=
            ITEM.register("iron_body_pot",()->new Iron_body_pot((new Item.Properties().stacksTo(16))));
    public static final RegistryObject<Item>EMPTY=
            ITEM.register("empty",()->new EmptyBottle((new Item.Properties().stacksTo(16))));










    public static final RegistryObject<Item> SULFUR_POT_SPRAY =
            ITEM.register("sulfur_pot_spray", () -> new Sulfur_pot_spray(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> DIME_TELE_POT_SPRAY =
            ITEM.register("dime_tele_pot_spray", () -> new Dime_tele_pot_spray(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> SEA_SPEED_POT_SPRAY =
            ITEM.register("sea_speed_pot_spray", () -> new Sea_speed_pot_spray(new Item.Properties().stacksTo(16)));





























    public static void register(IEventBus eventBus){ITEM.register(eventBus);
    }


}