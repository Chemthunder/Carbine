package silly.chemthunder.carbine.item;

import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import silly.chemthunder.carbine.Carbine;
import silly.chemthunder.carbine.index.CarbineDamageSources;
import silly.chemthunder.carbine.index.CarbineEnchantments;
import silly.chemthunder.carbine.index.CarbineSoundEvents;
import silly.chemthunder.carbine.index.CarbineStatusEffects;

import java.util.List;

public class ForgottenCursesItem extends AxeItem implements CustomKillSourceItem, KillEffectItem, CustomHitSoundItem {
    public ForgottenCursesItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(this.getDescription().withColor(0x97286a));
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return CarbineDamageSources.curse_kill(livingEntity);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {
        BlockPos victimPos = victim.getBlockPos();
        BlockPos downPos = victimPos.down();

        if (world.getBlockState(downPos).isIn(BlockTags.DIRT)) {
            world.setBlockState(victimPos, Blocks.CHERRY_SAPLING.getDefaultState());
        }

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(Carbine.GET_CURSED_BITCH, victim.getX(), victim.getY(), victim.getZ(), 15, 0.5, 0.5, 0.5, 0.05);
        }
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            if (!user.isOnGround()) {
                user.setVelocity(user.getRotationVec(0).multiply(2.5));
                user.velocityModified = true;
                ((PlayerEntity) user).getItemCooldownManager().set(this, 20);

                Box box = new Box(user.getBlockPos()).expand(4);
                List<LivingEntity> entities = world.getEntitiesByClass(
                        LivingEntity.class, box,
                        entity -> true
                );

                for (LivingEntity entity : entities) {
                    if (entity != user) {
                        entity.damage(CarbineDamageSources.rooted(entity), 1.5f);
                    }
                }
            } else {
                Box box = new Box(user.getBlockPos()).expand(8);
                List<LivingEntity> entities = world.getEntitiesByClass(
                        LivingEntity.class, box,
                        entity -> true
                );

                for (LivingEntity entity : entities) {
                    if (entity != user) {
                        if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, CarbineEnchantments.BOTANY)) {
                        entity.damage(CarbineDamageSources.rooted(user), 5f);
                        } else {
                            entity.addStatusEffect(new StatusEffectInstance(CarbineStatusEffects.ROOTED, 200));
                        }
                        ((PlayerEntity) user).getItemCooldownManager().set(this, 60);
                    }
                }
                ((PlayerEntity) user).getItemCooldownManager().set(this, 60);
            }
        }
        user.playSound(SoundEvents.BLOCK_CHERRY_LEAVES_BREAK, 0.5f, 0);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, CarbineEnchantments.BOTANY)) {
                target.setVelocity(attacker.getRotationVec(0).multiply(-0.4));
            }
            if (EnchantmentHelper.hasAnyEnchantmentsWith(stack, CarbineEnchantments.NEUROTOXIN)) {
                target.addStatusEffect(new StatusEffectInstance(CarbineStatusEffects.NEUROTOXIN, 600));
            }

            if (!EnchantmentHelper.hasAnyEnchantmentsWith(stack, CarbineEnchantments.BOTANY) || !EnchantmentHelper.hasAnyEnchantmentsWith(stack, CarbineEnchantments.NEUROTOXIN)) {
            target.addStatusEffect(new StatusEffectInstance(CarbineStatusEffects.ROOTED, 200));
            }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity, Entity entity) {
        playerEntity.playSound(SoundEvents.BLOCK_CHERRY_WOOD_BREAK);
        playerEntity.playSound(CarbineSoundEvents.CURSE, 0.5f, 1);
    }
}
