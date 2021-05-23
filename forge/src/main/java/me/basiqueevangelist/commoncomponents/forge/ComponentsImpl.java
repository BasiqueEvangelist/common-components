package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.DeferredComponentRef;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.function.Supplier;

public class ComponentsImpl {
    public static <T extends Component> void registerDeferred(DeferredComponentRef<T> ref, Identifier componentId, Class<T> componentInterface, Supplier<T> defaultFactory) {
        CommonComponentsForge.COMMON_SETUP_TASKS.add(() -> {
            CapabilityManager.INSTANCE.register(componentInterface, new ForgeComponentStorage<>(), defaultFactory::get);

            ref.setRef(get(componentId, componentInterface));
        });
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> ComponentRef<T> get(Identifier componentId, Class<T> componentInterface) {
        IdentityHashMap<String, Capability<?>> providersMap;
        try {
            Field f = CapabilityManager.class.getDeclaredField("providers");
            f.setAccessible(true);
            providersMap = (IdentityHashMap<String, Capability<?>>) f.get(CapabilityManager.INSTANCE);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        return new CapabilityComponentRef<>((Capability<T>) providersMap.get(componentInterface.getCanonicalName()), componentInterface, componentId);
    }
}
