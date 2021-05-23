package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComponentCapProvider<T extends Component> implements ICapabilitySerializable<CompoundTag> {
    private final CapComponentRef<T> ref;
    private final T capInstance;

    public ComponentCapProvider(CapComponentRef<T> ref, T capInstance) {
        this.ref = ref;
        this.capInstance = capInstance;
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public <C> LazyOptional<C> getCapability(@NotNull Capability<C> capability, @Nullable Direction arg) {
        if (capability != ref.getCapability())
            return LazyOptional.empty();

        return LazyOptional.of(() -> (C) capInstance);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        capInstance.toTag(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag arg) {
        capInstance.fromTag(arg);
    }
}
