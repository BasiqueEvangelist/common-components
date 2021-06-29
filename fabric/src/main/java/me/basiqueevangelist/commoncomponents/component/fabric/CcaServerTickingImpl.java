package me.basiqueevangelist.commoncomponents.component.fabric;

import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import me.basiqueevangelist.commoncomponents.component.ServerTickedComponent;

public interface CcaServerTickingImpl extends ServerTickingComponent {
    ServerTickedComponent getWrapped();

    default void serverTick() { getWrapped().onServerTick(); }
}
