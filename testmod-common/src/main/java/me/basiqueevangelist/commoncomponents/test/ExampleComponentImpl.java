package me.basiqueevangelist.commoncomponents.test;

import me.basiqueevangelist.commoncomponents.SyncingComponent;
import net.minecraft.nbt.CompoundTag;

import java.util.Objects;

public class ExampleComponentImpl implements ExampleComponent, SyncingComponent {
    private int value = 0;

    public ExampleComponentImpl(Object attachedTo) {
        System.out.println("Called! " + (attachedTo == null ? "null" : attachedTo.getClass()));
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
}
