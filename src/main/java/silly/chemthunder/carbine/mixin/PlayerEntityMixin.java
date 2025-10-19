package silly.chemthunder.carbine.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import silly.chemthunder.carbine.index.CarbineStatusEffects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    private Text herald$maskName(Text original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            return Text.translatable("name.change").withColor(0xeda1dc).formatted(Formatting.ITALIC).formatted(Formatting.OBFUSCATED);
        }
        return original;
    }
}
