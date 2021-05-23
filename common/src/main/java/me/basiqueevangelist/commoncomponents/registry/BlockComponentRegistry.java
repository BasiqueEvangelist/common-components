package me.basiqueevangelist.commoncomponents.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Function;

public interface BlockComponentRegistry {
    <T extends Component, B extends BlockEntity> void registerFor(Class<B> target, ComponentRef<T> ref, Function<B, T> factory);
}
