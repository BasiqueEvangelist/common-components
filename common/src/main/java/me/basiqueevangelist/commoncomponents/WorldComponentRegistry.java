package me.basiqueevangelist.commoncomponents;

import net.minecraft.world.World;

import java.util.function.Function;

public interface WorldComponentRegistry {
    <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory);
}
