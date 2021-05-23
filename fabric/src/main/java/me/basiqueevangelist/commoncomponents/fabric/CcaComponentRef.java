package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
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
    public @Nullable T getFromHolder(Object holder) {
        return wrapped.getNullable(holder).getWrapped();
    }

    @Override
    public Identifier getComponentId() {
        return wrapped.getId();
    }

    @Override
    public Class<T> getComponentClass() {
        return wrappedType;
    }
}
