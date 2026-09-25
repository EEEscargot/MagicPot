package magic_pot.potions_ues;

import magic_pot.magic_pot.ModItem;
import magic_pot.magic_pot.Modpotion;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EmptyBottle extends Item {

    public EmptyBottle(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        BlockHitResult hitResult = getPlayerPOVHitResult(
                level,
                player,
                ClipContext.Fluid.SOURCE_ONLY
        );

        if (hitResult.getType() == HitResult.Type.BLOCK) {

            if (level.getFluidState(hitResult.getBlockPos())
                    .is(FluidTags.WATER)) {

                ItemStack result =
                        new ItemStack(Modpotion.WATER.get());

                return InteractionResultHolder.sidedSuccess(
                        ItemUtils.createFilledResult(
                                stack,
                                player,
                                result
                        ),
                        level.isClientSide()
                );
            }
        }

        return InteractionResultHolder.pass(stack);
    }
}