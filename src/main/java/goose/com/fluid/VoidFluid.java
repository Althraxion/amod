package goose.com.fluid;

import com.google.common.hash.Hashing;
import goose.com.VoidMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class VoidFluid extends FlowableFluid {

    @Override
    protected boolean isInfinite() {
        return false;
    } //doesn't allow for infinite sources like water.

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity); //i forgor
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return world.getDimension().ultrawarm() ? 4 : 2;
    } //flowspeed, self-explanatory

    @Override
    protected boolean hasRandomTicks() {
        return true; //random ticks, required for block breaking
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    } //idk

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    } //idk

    @Override
    public int getLevel(FluidState state) {
        return 0;
    } //idk

    @Override
    public int getTickRate(WorldView world) {
        return world.getDimension().ultrawarm() ? 10 : 30;
    } //tickrate

    @Override
    protected float getBlastResistance() {
        return 100f;
    } //blast res

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false; //cannot be replaced with anything
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_VOID;
    } //still

    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_VOID;
    } //flow

    @Override
    public Item getBucketItem() {
        return Items.AIR;
    } //to be changed to air

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModFluids.VOID_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state)); //the block
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    } //is not still yet

    @Override
    protected void onRandomTick(World world, BlockPos pos, FluidState state, Random random) {
        if (state.isStill() && random.nextInt(100) == 0 && world.getBlockState(pos).isOf(ModFluids.VOID_BLOCK)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        Direction dir = Direction.byId(random.nextInt(5)+1);
        BlockPos toDestroy = pos.offset(dir);
        BlockState desState = world.getBlockState(toDestroy);
        if (!desState.isOf(ModFluids.VOID_BLOCK)) {
            float hard = desState.getHardness(world, toDestroy);
            if (hard >= 0 && (random.nextFloat()*20) > hard) {
                world.breakBlock(toDestroy, false); //randomly breaks nearby blocks, requires random tick.
            }
        }
    }
    public static class Flowing extends VoidFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL); //the builder
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        } //the level. DO NOT MAKE 8!

        @Override
        public boolean isStill(FluidState state) {
            return false;
        } //this is the flow class, so not still
    } //FLOW

    public static class Still extends VoidFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        } //the level, should be 8 here

        @Override
        public boolean isStill(FluidState state) {
            return true;
        } //this is the still class, so still
    } //STILL
}
