package magic_pot.datagen.loot;

import magic_pot.magic_pot.AddItemModifier;
import magic_pot.magic_pot.Magic_pot;
import magic_pot.magic_pot.ModItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;


public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {

        public ModGlobalLootModifiersProvider(PackOutput output) {super(output, Magic_pot.MODID);}

        @Override
        protected void start() {
            add("aband_shell_from_buried_treasure",new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder( new ResourceLocation("chests/buried_treasure")).build() }, ModItem.ABAND_SHELl.get(),1));
            add("disc_hell_from_nether_bridge",new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder( new ResourceLocation("chests/nether_bridge")).build(), LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItem.DISC_HELL.get(),1));
            add("sulfur_from_coal",new AddItemModifier(new LootItemCondition[]{
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COAL_ORE).build(),
                    new LootTableIdCondition.Builder(new ResourceLocation("blocks/coal_ore")).build()}, ModItem.SULFUR.get(),4));
            add("sulfur_from_deepslate_coal_ore",new AddItemModifier(new LootItemCondition[]{
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DEEPSLATE_COAL_ORE).build(),
                    new LootTableIdCondition.Builder(new ResourceLocation("blocks/deepslate_coal_ore")).build()}, ModItem.SULFUR.get(),4));

    }
}
