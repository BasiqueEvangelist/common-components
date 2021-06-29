package me.basiqueevangelist.commoncomponents.component;

public interface CopyableComponent<SELF extends CopyableComponent<SELF>> {
    void copyFrom(SELF other);
}
