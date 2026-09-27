package magic_pot.itemUse;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static magic_pot.magic_pot.ModItem.ABAND_SHELl;

public class Marine_qj_return extends Item {
    public Marine_qj_return(Properties pProperties){
        super(pProperties);
    }
    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack resultStack = super.finishUsingItem(pStack, pLevel, pEntityLiving);

        if (pEntityLiving instanceof Player player && !player.getAbilities().instabuild) {

            ItemStack Aband_shell = new ItemStack(ABAND_SHELl.get());

            if (resultStack.isEmpty()) {
                return Aband_shell;
            }

            // 防御性代码：吃掉 1 个后塞给玩家背包
            if (!player.getInventory().add(Aband_shell)) {
                player.drop(Aband_shell, false); // 如果背包满了，掉落在地上
            }
        }
        return resultStack;
    }
}
