package ru.xpoft.vaadin.sample.integration.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
@VaadinView(value = UIScopedView.NAME, cached = true)
public class UIScopedView extends Panel implements View
{
    public static final String NAME = "ui_scoped";

    private final Label statusLabel = new Label();
    private boolean scoped = false;

    public UIScopedView()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        setSizeFull();
        ((VerticalLayout)getContent()).setSpacing(true);

        HorizontalLayout status = new HorizontalLayout();

        addComponent(new Label("At first time, it's loading about 5 seconds."));
        addComponent(new Label("Now click \"Go back\" button, than \"Go to the UI scoped View\" again."));
        addComponent(statusLabel);
        addComponent(new Button("Go back", new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                Page.getCurrent().setFragment("!" + MainView.NAME);
            }
        }));

        statusLabel.setCaption("State");
        statusLabel.setValue("First time");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {
        if (scoped)
        {
            statusLabel.setValue("Already scoped");
        }

        scoped = true;
    }
}
