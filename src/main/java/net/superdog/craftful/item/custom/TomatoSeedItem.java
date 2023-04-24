package net.superdog.craftful.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.superdog.craftful.block.ModBlocks;


public class TomatoSeedItem extends AliasedBlockItem {
    public TomatoSeedItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = context.getPlayer();
        Hand hand = context.getHand();
        ItemStack stack = player.getStackInHand(hand);

        if (state.isOf(Blocks.FARMLAND)) {
            world.setBlockState(pos.up(), ModBlocks.TOMATO_CROP.getDefaultState());
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            return ActionResult.success(world.isClient);
        } else {
            return super.useOnBlock(context);
        }
    }
}