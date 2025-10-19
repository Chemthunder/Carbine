package silly.chemthunder.carbine.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import silly.chemthunder.carbine.Carbine;

import java.util.concurrent.CompletableFuture;

public class CarbineItemTagProvider extends FabricTagProvider<Item> {
    public CarbineItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    public static final TagKey<Item> FORG_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Carbine.MOD_ID, "forg_items"));
    public static final TagKey<Item> CARN_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Carbine.MOD_ID, "carn_items"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(FORG_ITEMS)
                .add(Items.CHERRY_SAPLING)
                .setReplace(true);
        getOrCreateTagBuilder(CARN_ITEMS)
                .add(Items.AMETHYST_CLUSTER)
                .setReplace(true);
    }
}
