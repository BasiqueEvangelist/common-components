package me.basiqueevangelist.commoncomponents;

public interface CopyableComponent<SELF extends CopyableComponent<SELF>> {
    void copyFrom(SELF other);
}
