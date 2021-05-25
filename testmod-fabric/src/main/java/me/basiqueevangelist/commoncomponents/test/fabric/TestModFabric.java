package me.basiqueevangelist.commoncomponents.test.fabric;

import me.basiqueevangelist.commoncomponents.test.TestMod;
import net.fabricmc.api.ModInitializer;

public class TestModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TestMod.init();
    }
}
