package goose.com;

import goose.com.entity.ModEntity;
import goose.com.fluid.ModFluids;
import goose.com.tag.ModFluidTags;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoidMod implements ModInitializer {
    public static final String MOD_ID = "voidmod";
    public static final Logger LOGGER =  LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {

        ModFluids.register();

    }
}
