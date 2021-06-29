package me.basiqueevangelist.commoncomponents.component.registry;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import net.minecraft.world.World;

import java.util.function.Function;

public interface WorldComponentRegistry {
    <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory);
}
