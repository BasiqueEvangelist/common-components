package me.basiqueevangelist.commoncomponents.component.fabric;

import dev.onyxstudios.cca.api.v3.component.tick.ClientTickingComponent;
import me.basiqueevangelist.commoncomponents.component.ClientTickedComponent;

public interface CcaClientTickingImpl extends ClientTickingComponent {
    ClientTickedComponent getWrapped();

    default void clientTick() { getWrapped().onClientTick(); }
}
