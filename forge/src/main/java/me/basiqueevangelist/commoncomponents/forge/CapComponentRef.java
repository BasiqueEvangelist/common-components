package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.forge.sync.CapSyncing;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class CapComponentRef<T extends Component> implements ComponentRef<T> {
    private final Capability<T> capability;
    private final Class<T> capClass;
    private final Identifier capId;

    public CapComponentRef(Capability<T> capability, Class<T> capClass, Identifier capId) {
        this.capability = capability;
        this.capClass = capClass;
        this.capId = capId;
    }

    public Capability<T> getCapability() {
        return capability;
    }

    @Override
    public @Nullable T getFromHolder(Object provider) {
        return ((ICapabilityProvider) provider).getCapability(capability).orElse(null);
    }

    @Override
    public Identifier getComponentId() {
        return capId;
    }

    @Override
    public Class<T> getComponentClass() {
        return capClass;
    }

    @Override
    public void sync(Object provider) {
        CapSyncing.syncCap((ICapabilityProvider) provider, capability);
    }
}
