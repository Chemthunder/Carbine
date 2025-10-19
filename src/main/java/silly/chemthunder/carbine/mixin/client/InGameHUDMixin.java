package silly.chemthunder.carbine.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silly.chemthunder.carbine.Carbine;
import silly.chemthunder.carbine.index.CarbineStatusEffects;

@Mixin(InGameHud.class)
public abstract class InGameHUDMixin {
    @Unique
    private static final Identifier ROOTED_HEARTS = Carbine.id("hud/heart/rooted_heart");

//    @Inject(method = "drawHeart", at = @At("HEAD"), cancellable = true)
//    private void thulium$drawCustomHeart(DrawContext context, InGameHud.HeartType type, int x, int y, boolean hardcore, boolean blinking, boolean half, CallbackInfo ci) {
//        if (!blinking && type == InGameHud.HeartType.NORMAL && MinecraftClient.getInstance().cameraEntity instanceof PlayerEntity player && (player.hasStatusEffect(CarbineStatusEffects.ROOTED))) {
//            Identifier textureId;
//            if (player.hasStatusEffect(CarbineStatusEffects.ROOTED)) {
//                textureId = ROOTED_HEARTS;
//            } else {
//                return;
//            }
//            context.drawTexture(textureId, x, y, half ? 9 : 0, 0, 9, 9);
//            ci.cancel();
//        }
//    }


    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN) || MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity livingEntity && livingEntity.hasStatusEffect(CarbineStatusEffects.ROOTED)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void renderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void renderHotbar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void renderArmor(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private static void renderExpBar(DrawContext context, int x, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void renderExpLvl(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderOverlay(DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private static void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private static void renderStatusEffectOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderMainHud", at = @At("HEAD"), cancellable = true)
    private static void renderMainHudOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHotbarItem", at = @At("HEAD"), cancellable = true)
    private static void renderHotBarItem(DrawContext context, int x, int y, RenderTickCounter tickCounter, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci) {
        if (MinecraftClient.getInstance().getCameraEntity() instanceof LivingEntity living && living.hasStatusEffect(CarbineStatusEffects.NEUROTOXIN)) {
            ci.cancel();
        }
    }
}
