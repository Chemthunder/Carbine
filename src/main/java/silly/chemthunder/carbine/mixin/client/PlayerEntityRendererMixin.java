package silly.chemthunder.carbine.mixin.client;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.SkinTextures;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import silly.chemthunder.carbine.Carbine;
import silly.chemthunder.carbine.index.CarbineStatusEffects;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "getTexture(Lnet/minecraft/client/network/AbstractClientPlayerEntity;)Lnet/minecraft/util/Identifier;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getTexture(AbstractClientPlayerEntity player, CallbackInfoReturnable<Identifier> cir) {
        if (player.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            cir.setReturnValue(Carbine.id("textures/entity/the_forgotten.png"));
        }
    }

    @Redirect(
            method = "renderArm",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getSkinTextures()Lnet/minecraft/client/util/SkinTextures;"
            )
    )
    private SkinTextures herald$armSkin(AbstractClientPlayerEntity instance) {
        SkinTextures defaultTextures = instance.getSkinTextures();
        if (instance.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            return new SkinTextures(
                    Carbine.id("textures/entity/the_forgotten.png"),
                    defaultTextures.textureUrl(),
                    defaultTextures.capeTexture(),
                    defaultTextures.elytraTexture(),
                    SkinTextures.Model.SLIM,
                    defaultTextures.secure()
            );
        }
        return defaultTextures;
    }
}
