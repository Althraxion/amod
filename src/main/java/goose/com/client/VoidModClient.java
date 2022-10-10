package goose.com.client;

import goose.com.fluid.ModFluids;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class VoidModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_VOID, ModFluids.FLOWING_VOID,
                new SimpleFluidRenderHandler(
                        new Identifier("voidmod:block/void_still.png"),
                        new Identifier("voidmod:block/void_flowing.png"),
                        00000000
                ));

        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("voidmod:block/void_still"));
            registry.register(new Identifier("modid:block/void_flowing"));
        });
    }
}
