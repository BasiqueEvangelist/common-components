package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.EntityComponentRegistry;
import net.minecraft.entity.Entity;

import java.util.function.Function;

public class CcaEntityComponentRegistry implements EntityComponentRegistry {
    private final EntityComponentFactoryRegistry ecfr;

    public CcaEntityComponentRegistry(EntityComponentFactoryRegistry ecfr) {
        this.ecfr = ecfr;
    }

    @Override
    public <T extends Component, E extends Entity> void registerFor(Class<E> target, ComponentRef<T> ref, Function<E, ? extends T> factory) {
        ecfr.registerFor(target, ((CcaComponentRef<T>) ref).getWrapped(), entity -> new CcaComponent<>(factory.apply(entity)));
    }
}
