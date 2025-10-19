package silly.chemthunder.carbine.index;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import silly.chemthunder.carbine.datagen.CarbineItemTagProvider;

public interface CarbineToolMaterials {
   // ToolMaterial SOLI = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 99999999, 5, 0, 5, HeraldItemTagProvider.SOLTIUDE_ITEMS);

    ToolMaterial FORG = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 9999999, 10, 0, 5, CarbineItemTagProvider.FORG_ITEMS);
    ToolMaterial CARN = create(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 9999999, 10, 0, 5, CarbineItemTagProvider.CARN_ITEMS);


    private static ToolMaterial create(TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {

        return new ToolMaterial() {
            @Override
            public int getDurability() {
                return durability;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return miningSpeed;
            }

            @Override
            public float getAttackDamage() {
                return attackDamageBonus;
            }

            @Override
            public TagKey<Block> getInverseTag() {
                return incorrectBlocksForDrops;
            }

            @Override
            public int getEnchantability() {
                return enchantmentValue;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.fromTag(repairItems);
            }
        };
    }
}
