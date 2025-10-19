package silly.chemthunder.carbine.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import silly.chemthunder.carbine.index.CarbineItems;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract boolean isOf(Item item);

    @ModifyReturnValue(method = "getDamage", at = @At("RETURN"))
    private int noDamage(int original) {
        if (this.isOf(CarbineItems.FORGOTTEN_CURSES) || this.isOf(CarbineItems.CARNATION)) {
            return 0;
        }

        return original;
    }
}