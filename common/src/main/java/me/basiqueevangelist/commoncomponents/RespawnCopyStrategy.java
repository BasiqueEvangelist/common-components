package me.basiqueevangelist.commoncomponents;

import net.minecraft.nbt.CompoundTag;

public interface RespawnCopyStrategy<T extends Component> {
    void copy(T from, T to, boolean lossless, boolean withInventory);

    RespawnCopyStrategy<Component> ALWAYS = (from, to, lossless, withInventory) -> doCopy(from, to);
    RespawnCopyStrategy<Component> INVENTORY = (from, to, lossless, withInventory) -> {
        if (withInventory || lossless) {
            doCopy(from, to);
        }
    };
    RespawnCopyStrategy<Component> LOSSLESS = (from, to, lossless, withInventory) -> {
        if (lossless) {
            doCopy(from, to);
        }
    };
    RespawnCopyStrategy<Component> NEVER = (from, to, lossless, withInventory) -> {};

    @SuppressWarnings({"rawtypes", "unchecked"})
    static <T extends Component> void doCopy(T from, T to) {
        if (to instanceof CopyableComponent<?>) {
            ((CopyableComponent) to).copyFrom((CopyableComponent) from);
        } else {
            CompoundTag tag = new CompoundTag();
            from.toTag(tag);
            to.fromTag(tag);
        }
    }
}
