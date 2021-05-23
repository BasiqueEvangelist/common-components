package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.WorldComponentRegistry;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapabilityWorldComponentRegistry implements WorldComponentRegistry {
    private final AttachCapabilitiesEvent<World> event;

    public CapabilityWorldComponentRegistry(AttachCapabilitiesEvent<World> event) {
        this.event = event;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory) {
        event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply(event.getObject())));
    }
}
