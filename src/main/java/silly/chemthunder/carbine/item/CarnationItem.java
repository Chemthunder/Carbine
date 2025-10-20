package silly.chemthunder.carbine.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import silly.chemthunder.carbine.index.CarbineDamageSources;

public class CarnationItem extends SwordItem implements CustomHitSoundItem, CustomHitParticleItem, CustomKillSourceItem {
    public CarnationItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0xf5bae8, 0xd675c0)};

    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    public DamageSource getKillSource(LivingEntity livingEntity) {
        return CarbineDamageSources.carnation_kill(livingEntity);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BRUSH;
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return Integer.MAX_VALUE;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(this, 90);
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public void spawnHitParticles(PlayerEntity playerEntity, Entity entity) {
        spawnHitParticles(playerEntity);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK);
    }
}
