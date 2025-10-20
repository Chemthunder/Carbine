package silly.chemthunder.carbine.index;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import silly.chemthunder.carbine.Carbine;
import silly.chemthunder.carbine.effect.ImpededEffect;
import silly.chemthunder.carbine.effect.NeurotoxinEffect;
import silly.chemthunder.carbine.effect.RootedEffect;

public interface CarbineStatusEffects {
    RegistryEntry<StatusEffect> ROOTED = create("rooted", new RootedEffect(StatusEffectCategory.HARMFUL, 0x0000));
    RegistryEntry<StatusEffect> NEUROTOXIN = create("neurotoxin", new NeurotoxinEffect(StatusEffectCategory.HARMFUL, 0x0000));
    RegistryEntry<StatusEffect> IMPEDED = create("impeded", new ImpededEffect(StatusEffectCategory.NEUTRAL, 0x0000));

    private static RegistryEntry<StatusEffect> create(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Carbine.id(name), statusEffect);
    }

    static void init() {
    }
}
