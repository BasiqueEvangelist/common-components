package me.basiqueevangelist.commoncomponents.test;

import me.basiqueevangelist.commoncomponents.component.ClientTickedComponent;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ServerTickedComponent;
import me.basiqueevangelist.commoncomponents.component.SyncingComponent;

public interface ExampleComponent extends Component, SyncingComponent, ClientTickedComponent, ServerTickedComponent {
    int getValue();

    void setValue(int value);
}
