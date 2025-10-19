package silly.chemthunder.carbine;

import net.acoyt.acornlib.api.ALib;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.carbine.index.*;

public class Carbine implements ModInitializer {
	public static final String MOD_ID = "carbine";

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path);
    }
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        CarbineItems.init();
        CarbineItemGroups.init();
        CarbineSoundEvents.init();
        CarbineStatusEffects.init();
        CarbineEnchantments.init();

        ALib.registerModIcon(MOD_ID, Carbine.id("lil_guy_but_he_sparkles.png"));
        ALib.registerModMenu(MOD_ID, 0x97286a);
		LOGGER.info("Carbine initialized.");
	}
}