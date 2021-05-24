package me.basiqueevangelist.commoncomponents.forge;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;

@SuppressWarnings("unchecked")
public final class CapManagerUtils {
    private CapManagerUtils() {

    }

    public static final IdentityHashMap<String, Capability<?>> providersMap;

    static {
        try {
            Field f = CapabilityManager.class.getDeclaredField("providers");
            f.setAccessible(true);
            providersMap = (IdentityHashMap<String, Capability<?>>) f.get(CapabilityManager.INSTANCE);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}
