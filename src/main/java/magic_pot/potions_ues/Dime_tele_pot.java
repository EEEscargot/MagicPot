package magic_pot.potions_ues;

import magic_pot.magic_pot.ModEffects;
import magic_pot.magic_pot.Modpotion;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;


public class Dime_tele_pot extends Item {
    public Dime_tele_pot(Item.Properties properties) {
        super(properties);
    }
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 18;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand) {

        player.startUsingItem(hand);

        return InteractionResultHolder.consume(
                player.getItemInHand(hand)
        );
    }
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        // 仅在服务端执行传送
        if (!level.isClientSide()){
            livingEntity.addEffect(new MobEffectInstance(ModEffects.DIME_TELE_EFFECT.get(), 30 * 20, 0));
            // 随机生成传送偏移
            double range = 100.0 + level.random.nextDouble() * 8.0;
            double angle = level.random.nextDouble() * 2 * Math.PI;
            double dx = Math.cos(angle) * range;
            double dz = Math.sin(angle) * range;

            double targetX = livingEntity.getX() + dx;
            double targetZ = livingEntity.getZ() + dz;
            double targetY = livingEntity.getY();   // randomTeleport 会自动调整 Y 到安全高度

            // 执行原版安全随机传送
            livingEntity.randomTeleport(targetX, targetY, targetZ, true);
        }

        if (livingEntity instanceof Player player) {
            // 创造模式：不消耗物品，不返还空瓶
            if (player.isCreative()) {
                return stack;
            }

            // 生存/冒险模式：消耗一个物品
            stack.shrink(1);

            // 判断物品是否被用完（即原本数量为 1）
            if (stack.isEmpty()) {
                // 物品用完了，直接返回一个空瓶
                return new ItemStack(Modpotion.EMPTY.get());
            } else {
                // 物品还有剩余（比如你一次喝了多瓶中的一瓶）
                // 尝试将空瓶放入玩家背包
                if (!player.getInventory().add(new ItemStack(Modpotion.EMPTY.get()))) {
                    // 背包满了，把空瓶丢在地上
                    player.drop(new ItemStack(Modpotion.EMPTY.get()), false);
                }
                return stack;
            }
        }
        return stack;
    }
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 16;
    }

}

