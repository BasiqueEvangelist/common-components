package me.basiqueevangelist.commoncomponents.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.world.World;

import java.util.function.Function;

public interface WorldComponentRegistry {
    <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory);
}
