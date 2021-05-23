package me.basiqueevangelist.commoncomponents;

import net.minecraft.nbt.CompoundTag;

public interface Component {
    void toTag(CompoundTag tag);

    void fromTag(CompoundTag tag);
}
