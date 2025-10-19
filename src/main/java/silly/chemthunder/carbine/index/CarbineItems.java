package silly.chemthunder.carbine.index;

import net.acoyt.acornlib.api.item.AcornItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.carbine.Carbine;
import silly.chemthunder.carbine.item.CarnationItem;
import silly.chemthunder.carbine.item.ForgottenCursesItem;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public interface CarbineItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
    //  Item MACHINE_OIL_BOTTLE = create("machine_oil_bottle", new OilItem(new Item.Settings()
    //         .maxCount(16)
    //  ));

    Item FORGOTTEN_CURSES = create("forgotten_curses", new ForgottenCursesItem(CarbineToolMaterials.FORG, new AcornItemSettings()
            .maxCount(1)
            .fireproof()
            .attributeModifiers(SwordItem.createAttributeModifiers(CarbineToolMaterials.FORG, 8, -2.4f)))
    );

    Item CARNATION = create("carnation", new CarnationItem(CarbineToolMaterials.CARN, new AcornItemSettings()
            .maxCount(1)
            .fireproof()
            .attributeModifiers(SwordItem.createAttributeModifiers(CarbineToolMaterials.CARN, 9, -2.7f)))
    );

    static void init() {
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));

       // modifyItemNameColor(SOLITUDE, 0x1c1c21);
        modifyItemNameColor(FORGOTTEN_CURSES, 0xeda1dc);
        modifyItemNameColor(CARNATION, 0xf5bae8);
    }
    private static Item create(String name, Item item) {
        ITEMS.put(item, Carbine.id(name));
        return item;
    }
}
