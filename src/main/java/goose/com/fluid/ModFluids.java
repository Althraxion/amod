package goose.com.fluid;

import goose.com.VoidMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModFluids {
    public static FlowableFluid STILL_VOID;
    public static FlowableFluid FLOWING_VOID;
    public static Block VOID_BLOCK;

    public static void register() {
        STILL_VOID = Registry.register(Registry.FLUID,
                new Identifier(VoidMod.MOD_ID, "void"), new VoidFluid.Still());
        FLOWING_VOID = Registry.register(Registry.FLUID,
                new Identifier(VoidMod.MOD_ID, "flowing_void"), new VoidFluid.Flowing());

        VOID_BLOCK = Registry.register(Registry.BLOCK, new Identifier(VoidMod.MOD_ID, "void_block"),
                new FluidBlock(ModFluids.STILL_VOID, FabricBlockSettings.copyOf(Blocks.LAVA)){ });
    }
}
