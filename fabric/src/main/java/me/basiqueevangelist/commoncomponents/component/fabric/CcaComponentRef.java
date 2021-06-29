package me.basiqueevangelist.commoncomponents.component.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class CcaComponentRef<T extends Component> implements ComponentRef<T> {
    private final ComponentKey<CcaComponent<T>> wrapped;
    private final Class<T> wrappedType;

    public CcaComponentRef(ComponentKey<CcaComponent<T>> wrapped, Class<T> wrappedType) {
        this.wrapped = wrapped;
        this.wrappedType = wrappedType;
    }

    public ComponentKey<CcaComponent<T>> getWrapped() {
        return wrapped;
    }

    @Override
    public @Nullable T getFromHolder(Object provider) {
        return wrapped.getNullable(provider).getWrapped();
    }

    @Override
    public Identifier getComponentId() {
        return wrapped.getId();
    }

    @Override
    public Class<T> getComponentClass() {
        return wrappedType;
    }

    @Override
    public void sync(Object provider) {
        wrapped.sync(provider);
    }
}
