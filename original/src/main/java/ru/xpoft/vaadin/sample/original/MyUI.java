package ru.xpoft.vaadin.sample.original;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.Random;

/**
 * @author xpoft
 */
@PreserveOnRefresh
public class MyUI extends UI
{
    private long instanceRandom = new Random().nextLong();
    private long requestRandom;

    @Override
    protected void init(VaadinRequest request) {

        // Create the content root layout for the UI
        VerticalLayout content = new VerticalLayout();
        setContent(content);

        requestRandom = new Random().nextLong();

        content.addComponent(new Label("InstanceRandom: " + instanceRandom));
        content.addComponent(new Label("RequestRandom: " + requestRandom));
    }
}
