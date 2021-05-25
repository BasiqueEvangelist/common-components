package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import me.basiqueevangelist.commoncomponents.ServerTickedComponent;

public interface CcaServerTickingImpl extends ServerTickingComponent {
    ServerTickedComponent getWrapped();

    default void serverTick() { getWrapped().onServerTick(); }
}
