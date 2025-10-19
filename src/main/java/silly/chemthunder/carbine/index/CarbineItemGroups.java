package silly.chemthunder.carbine.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import silly.chemthunder.carbine.Carbine;

public interface CarbineItemGroups {
    RegistryKey<ItemGroup> HERALD_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Carbine.id("carbine"));
    ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(CarbineItems.FORGOTTEN_CURSES))
            .displayName(Text.translatable("itemGroup.carbine").formatted(Formatting.OBFUSCATED).styled(style -> style.withColor(0x731839)))
            .build();


    static void init() {
        Registry.register(Registries.ITEM_GROUP, HERALD_GROUP_KEY, ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(HERALD_GROUP_KEY).register(CarbineItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        itemGroup.add(CarbineItems.FORGOTTEN_CURSES);
        itemGroup.add(CarbineItems.CARNATION);
    }
}
