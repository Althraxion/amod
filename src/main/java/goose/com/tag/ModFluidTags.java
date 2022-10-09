package goose.com.tag;

import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModFluidTags {
    public static final TagKey<Fluid> VOID = of("void");

    private ModFluidTags() {
    }

    private static TagKey<Fluid> of(String id) {
        return TagKey.of(Registry.FLUID_KEY, new Identifier(id));
    }
}
