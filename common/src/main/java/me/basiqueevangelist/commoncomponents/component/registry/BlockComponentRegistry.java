package me.basiqueevangelist.commoncomponents.component.registry;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Function;

public interface BlockComponentRegistry {
    <T extends Component, B extends BlockEntity> void registerFor(Class<B> target, ComponentRef<T> ref, Function<B, T> factory);
}
