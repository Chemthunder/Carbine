package silly.chemthunder.carbine.index;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import silly.chemthunder.carbine.Carbine;

public interface CarbineDamageSources {
    RegistryKey<DamageType> CURSE_KILL = of("curse_kill");
    RegistryKey<DamageType> ROOTED = of("rooted");
    RegistryKey<DamageType> CARNATION_KILL = of("carnation_kill");
    RegistryKey<DamageType> INHIBITED = of("inhibited");

    static DamageSource curse_kill(LivingEntity entity) {
        return entity.getDamageSources().create(CURSE_KILL); }

    static DamageSource rooted(LivingEntity entity) {
        return entity.getDamageSources().create(ROOTED); }

    static DamageSource carnation_kill(LivingEntity entity) {
        return entity.getDamageSources().create(CARNATION_KILL); }

    static DamageSource inhibited(LivingEntity entity) {
        return entity.getDamageSources().create(INHIBITED); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Carbine.id(name));
    }
}
