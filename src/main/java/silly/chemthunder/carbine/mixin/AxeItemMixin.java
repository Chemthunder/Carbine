package silly.chemthunder.carbine.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import silly.chemthunder.carbine.index.CarbineItems;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends MiningToolItem {
    public AxeItemMixin(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(material, effectiveBlocks, settings);
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    private void carbine$obtain(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();

        ItemStack mainStack = player.getMainHandStack();
        ItemStack offStack = player.getOffHandStack();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (mainStack.isOf(Items.NETHERITE_AXE) && offStack.isOf(Items.PINK_PETALS)) {
            if (world.getBlockState(pos).isOf(Blocks.CHERRY_SAPLING)) {
                mainStack.decrement(1);
                offStack.decrement(1);

                player.playSound(SoundEvents.ENTITY_IRON_GOLEM_DAMAGE);
                player.playSound(SoundEvents.BLOCK_CONDUIT_ACTIVATE);
                player.giveItemStack(CarbineItems.FORGOTTEN_CURSES.getDefaultStack());
            }
        }
    }
}
