package me.basiqueevangelist.commoncomponents.fabric;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.SyncingComponent;
import net.minecraft.nbt.CompoundTag;

public class CcaComponent<T extends Component> implements dev.onyxstudios.cca.api.v3.component.Component {
    protected final T wrapped;

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

    @SuppressWarnings("unchecked")
    public static <T extends Component> CcaComponent<T> getWrapperFor(T inst) {
        if (SyncingComponent.class.isAssignableFrom(inst.getClass()))
            return (CcaComponent<T>) new CcaSyncingComponent<>((SyncingComponent) inst);
        else
            return new CcaComponent<>(inst);
    }
}
