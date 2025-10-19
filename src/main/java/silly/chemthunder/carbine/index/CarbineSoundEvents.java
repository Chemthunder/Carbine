package silly.chemthunder.carbine.index;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import silly.chemthunder.carbine.Carbine;

import java.util.LinkedHashMap;
import java.util.Map;

public interface CarbineSoundEvents {
    Map<SoundEvent, Identifier> SOUNDS = new LinkedHashMap<>();

    SoundEvent CURSE = create("item.curse");

    private static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(Carbine.id(name));
        SOUNDS.put(soundEvent, Carbine.id(name));
        return soundEvent;
    }

    static void init() {
        SOUNDS.keySet().forEach(soundEvent -> {
            Registry.register(Registries.SOUND_EVENT, SOUNDS.get(soundEvent), soundEvent);
        });
    }
}
