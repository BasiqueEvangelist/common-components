package me.basiqueevangelist.commoncomponents.component.fabric.registry;

import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.component.registry.WorldComponentRegistry;
import net.minecraft.world.World;

import java.util.function.Function;

public class CcaWorldComponentRegistry implements WorldComponentRegistry {
    private final WorldComponentFactoryRegistry registry;

    public CcaWorldComponentRegistry(WorldComponentFactoryRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory) {
        registry.register(((CcaComponentRef<T>) ref).getWrapped(), w -> CcaComponent.getWrapperFor(factory.apply(w)));
    }
}
