package me.basiqueevangelist.commoncomponents.test;

import me.basiqueevangelist.commoncomponents.ClientTickedComponent;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ServerTickedComponent;
import me.basiqueevangelist.commoncomponents.SyncingComponent;

public interface ExampleComponent extends Component, SyncingComponent, ClientTickedComponent, ServerTickedComponent {
    int getValue();

    void setValue(int value);
}
