package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class ForgeComponentStorage<T extends Component> implements Capability.IStorage<T> {
    @Nullable
    @Override
    public Tag writeNBT(Capability<T> capability, T object, Direction arg) {
        CompoundTag tag = new CompoundTag();
        object.toTag(tag);
        return tag;
    }

    @Override
    public void readNBT(Capability<T> capability, T object, Direction arg, Tag arg2) {
        if (!(arg2 instanceof CompoundTag)) {
            throw new IllegalStateException("Capability must be a compound tag!");
        }

        object.fromTag((CompoundTag) arg2);
    }
}
