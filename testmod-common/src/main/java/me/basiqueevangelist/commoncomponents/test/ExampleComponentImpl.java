package me.basiqueevangelist.commoncomponents.test;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;

import java.util.Objects;

public class ExampleComponentImpl implements ExampleComponent {
    private int value = 0;
    private final Object attachedTo;

    public ExampleComponentImpl(Object attachedTo) {
        this.attachedTo = attachedTo;
    }

    @Override
    public void toTag(CompoundTag tag) {
        tag.putInt("Value", value);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        value = tag.getInt("Value");
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExampleComponentImpl that = (ExampleComponentImpl) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public void onClientTick() {
//        System.out.println("client tick");
    }

    @Override
    public void onServerTick() {
//        System.out.println("server tick");
    }

    @Override
    public void fromSyncPacket(PacketByteBuf buf) {
        System.out.println("Synced! " + attachedTo.getClass());
        fromTag(buf.readCompoundTag());
    }
}
