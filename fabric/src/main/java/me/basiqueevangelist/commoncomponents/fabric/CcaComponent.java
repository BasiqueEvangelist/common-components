package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.nbt.CompoundTag;

import java.util.Objects;

public class CcaComponent<T extends me.basiqueevangelist.commoncomponents.Component> implements Component {
    private final T wrapped;

    public CcaComponent(T wrapped) {
        this.wrapped = wrapped;
    }

    public T getWrapped() {
        return wrapped;
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        wrapped.fromTag(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        wrapped.toTag(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CcaComponent<?> that = (CcaComponent<?>) o;
        return wrapped.equals(that.wrapped);
    }
}
