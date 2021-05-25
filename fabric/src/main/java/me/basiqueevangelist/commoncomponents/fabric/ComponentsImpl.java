package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.DeferredComponentRef;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ComponentsImpl {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Component> void registerDeferred(DeferredComponentRef<T> ref, Identifier componentId, Class<T> componentInterface, Supplier<T> defaultFactory) {
        ref.setRef((CcaComponentRef<T>) new CcaComponentRef(ComponentRegistry.getOrCreate(componentId, CcaComponent.getWrapperForClass(componentInterface)), componentInterface));
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> ComponentRef<T> get(Identifier componentId, Class<T> componentInterface) {
        return new CcaComponentRef<>((ComponentKey<CcaComponent<T>>) ComponentRegistry.get(componentId), componentInterface);
    }
}
