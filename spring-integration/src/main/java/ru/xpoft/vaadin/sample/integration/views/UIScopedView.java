package ru.xpoft.vaadin.sample.integration.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
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
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        layout.addComponent(new Label("At first time, it's loading about 5 seconds."));
        layout.addComponent(new Label("Now click \"Go back\" button, than \"Go to the UI scoped View\" again."));
        layout.addComponent(statusLabel);
        layout.addComponent(new Button("Go back", new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                Page.getCurrent().setUriFragment("!" + MainView.NAME);
            }
        }));

        statusLabel.setCaption("State");
        statusLabel.setValue("First time");

        setContent(layout);
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
