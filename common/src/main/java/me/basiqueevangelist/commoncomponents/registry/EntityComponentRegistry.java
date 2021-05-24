package me.basiqueevangelist.commoncomponents.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.RespawnCopyStrategy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Function;

public interface EntityComponentRegistry {
    <T extends Component, E extends Entity> void registerFor(Class<E> target, ComponentRef<T> ref, Function<E, ? extends T> factory);

    <T extends Component, Impl extends T> void registerForPlayers(ComponentRef<T> ref, Function<PlayerEntity, Impl> factory, RespawnCopyStrategy<? super Impl> respawnStrategy);
    // TODO: Add other methods.
}
