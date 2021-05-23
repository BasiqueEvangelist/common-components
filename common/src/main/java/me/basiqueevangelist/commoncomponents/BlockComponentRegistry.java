package me.basiqueevangelist.commoncomponents;

import net.minecraft.block.entity.BlockEntity;

import java.util.function.Function;

public interface BlockComponentRegistry {
    <T extends Component, B extends BlockEntity> void registerFor(Class<B> target, ComponentRef<T> ref, Function<B, T> factory);
}
