package goose.com.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VoidBottleItem extends Item {
    public VoidBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!entity.isInvulnerable()) {
            entity.kill();
            ItemUsage.exchangeStack(stack, user, new ItemStack(Items.GLASS_BOTTLE));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            context.getWorld().breakBlock(new BlockPos
                            (positionClicked.getX(), positionClicked.getY(), positionClicked.getZ()),
                                false);
            var user = context.getPlayer();
            var hand = context.getHand();
            assert user != null;
            ItemStack stack = user.getStackInHand(hand);
            ItemUsage.exchangeStack(stack, user, new ItemStack(Items.GLASS_BOTTLE));
        }

        return ActionResult.SUCCESS;
    }
}
