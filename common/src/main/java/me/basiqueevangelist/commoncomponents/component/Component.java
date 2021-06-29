package me.basiqueevangelist.commoncomponents.component;

import net.minecraft.nbt.CompoundTag;

public interface Component {
    void toTag(CompoundTag tag);

    void fromTag(CompoundTag tag);
}
