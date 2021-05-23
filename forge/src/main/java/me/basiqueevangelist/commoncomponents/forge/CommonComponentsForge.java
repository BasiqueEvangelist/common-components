package me.basiqueevangelist.commoncomponents.forge;

import me.shedaniel.architectury.platform.forge.EventBuses;
import me.basiqueevangelist.commoncomponents.CommonComponents;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(CommonComponents.MOD_ID)
public class CommonComponentsForge {
    public static final List<Runnable> COMMON_SETUP_TASKS = new ArrayList<>();

    public CommonComponentsForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CommonComponents.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);

        ComponentEventHooksImpl.init();

        CommonComponents.init();
    }

    @SubscribeEvent
    protected void onCommonSetup(FMLCommonSetupEvent event) {
        COMMON_SETUP_TASKS.forEach(Runnable::run);
    }
}
