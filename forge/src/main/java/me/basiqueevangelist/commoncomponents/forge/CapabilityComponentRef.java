package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

public class CapabilityComponentRef<T extends Component> implements ComponentRef<T> {
    private final Capability<T> capability;
    private final Class<T> capClass;
    private final Identifier capId;

    public CapabilityComponentRef(Capability<T> capability, Class<T> capClass, Identifier capId) {
        this.capability = capability;
        this.capClass = capClass;
        this.capId = capId;
    }

    public Capability<T> getCapability() {
        return capability;
    }

    @Override
    public @Nullable T getFromHolder(Object holder) {
        return ((ICapabilityProvider) holder).getCapability(capability).orElse(null);
    }

    @Override
    public Identifier getComponentId() {
        return capId;
    }

    @Override
    public Class<T> getComponentClass() {
        return capClass;
    }
}
