package me.basiqueevangelist.commoncomponents;

import me.shedaniel.architectury.targets.ArchitecturyTarget;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public class DeferredComponentRef<T extends Component> {
    private ComponentRef<T> ref;

    public DeferredComponentRef(Identifier componentId, Class<T> componentInterface, Supplier<T> defaultFactory) {
        Components.registerDeferred(this, componentId, componentInterface, defaultFactory);
    }

    @ApiStatus.Internal
    public void setRef(ComponentRef<T> ref) {
        this.ref = ref;
    }

    public ComponentRef<T> get() {
        if (ref == null) {
            throw new IllegalStateException("Component ref wasn't registered yet!");
        }

        return ref;
    }

    public boolean wasRegistered() {
        return ref != null;
    }
}
