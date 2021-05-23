package me.basiqueevangelist.commoncomponents.fabric;

import me.basiqueevangelist.commoncomponents.CommonComponents;
import net.fabricmc.api.ModInitializer;

public class CommonComponentsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        CommonComponents.init();
    }
}
