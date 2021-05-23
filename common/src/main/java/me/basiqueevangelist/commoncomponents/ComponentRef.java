package me.basiqueevangelist.commoncomponents;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public interface ComponentRef<T extends Component> {
    @Nullable T getFromHolder(Object holder);

    Identifier getComponentId();

    Class<T> getComponentClass();
}
