package silly.chemthunder.carbine;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import silly.chemthunder.carbine.datagen.CarbineItemTagProvider;

public class CarbineDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.createPack().addProvider(CarbineItemTagProvider::new);
	}
}
