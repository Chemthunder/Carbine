package silly.chemthunder.carbine.effect;

import net.acoyt.acornlib.api.effect.UnclearableEffect;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ImpededEffect extends StatusEffect implements UnclearableEffect {
    public ImpededEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        BlockState state = entity.getBlockStateAtPos();
        if (entity instanceof PlayerEntity player) {
            player.slowMovement(state, new Vec3d(1, 0.2, 1));
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AIR.getDefaultState());
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 15;
        return duration % i == 0;
    }
}
