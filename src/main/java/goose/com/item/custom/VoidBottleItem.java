package goose.com.item.custom;

import goose.com.fluid.ModFluids;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.block.AirBlock;

public class VoidBottleItem extends Item {
    public VoidBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!entity.isInvulnerable()) {
            entity.kill();
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
