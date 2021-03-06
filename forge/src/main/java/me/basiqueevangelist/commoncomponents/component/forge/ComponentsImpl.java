package me.basiqueevangelist.commoncomponents.component.forge;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.DeferredComponentRef;
import me.basiqueevangelist.commoncomponents.forge.CommonComponentsForge;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.util.function.Supplier;

public class ComponentsImpl {
    public static <T extends Component> void registerDeferred(DeferredComponentRef<T> ref, Identifier componentId, Class<T> componentInterface, Supplier<T> defaultFactory) {
        CommonComponentsForge.COMMON_SETUP_TASKS.add(() -> {
            CapabilityManager.INSTANCE.register(componentInterface, new ComponentCapStorage<>(), defaultFactory::get);

            CapComponentRef<T> cap = (CapComponentRef<T>) get(componentId, componentInterface);
            CapTicking.caps.add(cap.getCapability());
            ref.setRef(cap);
        });
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> ComponentRef<T> get(Identifier componentId, Class<T> componentInterface) {
        return new CapComponentRef<>((Capability<T>) CapManagerUtils.getCapability(componentInterface.getCanonicalName()), componentInterface, componentId);
    }
}
