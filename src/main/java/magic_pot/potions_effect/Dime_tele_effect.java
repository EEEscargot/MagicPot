package magic_pot.potions_effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Dime_tele_effect extends MobEffect {
    public Dime_tele_effect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "8cb2e2f8-930c-4488-a651-971304c98cb7",
                -0.4D,
                AttributeModifier.Operation.MULTIPLY_TOTAL

        );
        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "690ddf5d-79ee-48c3-adf7-23e07ec82c95",
                -0.1D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                "f4fe4ef3-59f7-46a2-94d8-74d292691a67",
                -0.2,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
                Attributes.JUMP_STRENGTH,
                "8284a673-385a-47b0-9fe0-9b94fdd6a540",
                -0.1,
                AttributeModifier.Operation.ADDITION
        );
    }

}
