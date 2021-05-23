package me.basiqueevangelist.commoncomponents;

import net.minecraft.entity.Entity;

import java.util.function.Function;

public interface EntityComponentRegistry {
    <T extends Component, E extends Entity> void registerFor(Class<E> target, ComponentRef<T> ref, Function<E, ? extends T> factory);

    // TODO: Add other methods.
}
