package me.basiqueevangelist.commoncomponents.forge;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import user11681.reflect.Accessor;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

public final class CapManagerUtils {
    private CapManagerUtils() {

    }

    private static final IdentityHashMap<String, Capability<?>> providersMap = Accessor.getObject(CapabilityManager.INSTANCE, "providers");

    public static Capability<?> getCapability(String name) {
        return providersMap.get(name.intern());
    }
}
