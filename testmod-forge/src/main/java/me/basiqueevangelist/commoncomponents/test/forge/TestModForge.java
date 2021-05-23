package me.basiqueevangelist.commoncomponents.test.forge;

import me.basiqueevangelist.commoncomponents.test.TestMod;
import net.minecraftforge.fml.common.Mod;

@Mod(TestMod.MODID)
public class TestModForge {
    public TestModForge() {
        TestMod.init();
    }
}
