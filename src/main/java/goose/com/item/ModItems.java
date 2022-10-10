package goose.com.item;

import goose.com.VoidMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item VOID_BOTTLE = registerItem("void_bottle",
            new Item(new FabricItemSettings().group(ItemGroup.COMBAT).fireproof()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VoidMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        VoidMod.LOGGER.debug("Registering " + VoidMod.MOD_ID + " Items");
    }
}
