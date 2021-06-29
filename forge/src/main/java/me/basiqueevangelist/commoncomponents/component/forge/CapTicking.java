package me.basiqueevangelist.commoncomponents.component.forge;

import me.basiqueevangelist.commoncomponents.component.ClientTickedComponent;
import me.basiqueevangelist.commoncomponents.component.ServerTickedComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.ArrayList;
import java.util.List;

public final class CapTicking {
    private CapTicking() {

    }

    static final List<Capability<?>> caps = new ArrayList<>();

    public static void tickCapsServer(ICapabilityProvider provider) {
        for (Capability<?> cap : caps) {
            Object capInstance = provider.getCapability(cap).orElse(null);
            if (capInstance instanceof ServerTickedComponent)
                ((ServerTickedComponent) capInstance).onServerTick();
        }
    }

    public static void tickCapsClient(ICapabilityProvider provider) {
        for (Capability<?> cap : caps) {
            Object capInstance = provider.getCapability(cap).orElse(null);
            if (capInstance instanceof ClientTickedComponent)
                ((ClientTickedComponent) capInstance).onClientTick();
        }
    }
}
