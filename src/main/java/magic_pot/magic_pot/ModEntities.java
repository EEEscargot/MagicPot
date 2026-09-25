package magic_pot.magic_pot;

import magic_pot.entity.Dime_tele_pot_spray_enti;
import magic_pot.entity.Sea_speed_pot_spray_enti;
import magic_pot.entity.Sulfur_pot_spray_enti;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "magic_pot");

    public static final RegistryObject<EntityType<Sulfur_pot_spray_enti>> SULFUR_POT_SPRAY_ENTI =
            ENTITY_TYPES.register("sulfur_pot_spray", () ->
                    EntityType.Builder.<Sulfur_pot_spray_enti>of(Sulfur_pot_spray_enti::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .build("sulfur_pot_spray_enti"));

    public static final RegistryObject<EntityType<Dime_tele_pot_spray_enti>> DIME_TELE_POT_SPRAY_ENTI =
            ENTITY_TYPES.register("dime_tele_pot_spray", () ->
                    EntityType.Builder.<Dime_tele_pot_spray_enti>of(Dime_tele_pot_spray_enti::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .build("dime_tele_pot_spray_enti"));
    public static final RegistryObject<EntityType<Sea_speed_pot_spray_enti>>SEA_SPEED_POT_ENTI =
            ENTITY_TYPES.register("sea_speed_pot_enti", () ->
                    EntityType.Builder.<Sea_speed_pot_spray_enti>of(Sea_speed_pot_spray_enti::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .build("sea_speed_pot_enti"));








    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}