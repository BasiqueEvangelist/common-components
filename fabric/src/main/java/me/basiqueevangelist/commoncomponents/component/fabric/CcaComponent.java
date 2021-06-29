package me.basiqueevangelist.commoncomponents.component.fabric;

import me.basiqueevangelist.commoncomponents.component.ClientTickedComponent;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ServerTickedComponent;
import me.basiqueevangelist.commoncomponents.component.SyncingComponent;
import me.basiqueevangelist.commoncomponents.component.fabric.asm.CcaComponentSubclassGenerator;
import net.minecraft.nbt.CompoundTag;

import java.lang.reflect.InvocationTargetException;

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
        int flags = 0;
        if (inst instanceof SyncingComponent)
            flags |= CcaComponentSubclassGenerator.SYNCING;
        if (inst instanceof ClientTickedComponent)
            flags |= CcaComponentSubclassGenerator.CLIENT_TICKING;
        if (inst instanceof ServerTickedComponent)
            flags |= CcaComponentSubclassGenerator.SERVER_TICKING;
        try {
            return (CcaComponent<T>) CcaComponentSubclassGenerator.getClassFor(flags).getConstructor(Component.class).newInstance(inst);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Couldn't get CCA wrapper for component due to reflection exception", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Class<? extends CcaComponent<?>> getWrapperForClass(Class<?> klass) {
        int flags = 0;
        if (SyncingComponent.class.isAssignableFrom(klass))
            flags |= CcaComponentSubclassGenerator.SYNCING;
        if (ClientTickedComponent.class.isAssignableFrom(klass))
            flags |= CcaComponentSubclassGenerator.CLIENT_TICKING;
        if (ServerTickedComponent.class.isAssignableFrom(klass))
            flags |= CcaComponentSubclassGenerator.SERVER_TICKING;
        return (Class<? extends CcaComponent<?>>) CcaComponentSubclassGenerator.getClassFor(flags);
    }
}
