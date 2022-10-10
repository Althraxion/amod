package goose.com.item;

import goose.com.VoidMod;
import goose.com.item.custom.VoidBottleItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VoidMod.MOD_ID, name), item);
    }

    public static final Item VOID_BOTTLE = registerItem("void_bottle",
            new VoidBottleItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1)));

    public static void registerModItems() {
        VoidMod.LOGGER.debug("Registering " + VoidMod.MOD_ID + " Items");
    }
}
