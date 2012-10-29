package ru.xpoft.vaadin.sample.integration.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;
import ru.xpoft.vaadin.sample.integration.beans.ApplicationCounter;
import ru.xpoft.vaadin.sample.integration.beans.SessionCounter;
import ru.xpoft.vaadin.sample.integration.components.SimpleForm;

import javax.annotation.PostConstruct;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
@VaadinView(MainView.NAME)
public class MainView extends Panel implements View
{
    public static final String NAME = "";

    @Autowired
    private SimpleForm form;

    @Autowired
    private SessionCounter sessionCounter;

    @Autowired
    private ApplicationCounter applicationCounter;

    @PostConstruct
    public void PostConstruct()
    {
        setSizeFull();
        ((VerticalLayout)getContent()).setSpacing(true);

        addComponent(form);

        addComponent(new Button("Show counts", new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                Notification.show(String.format("Session counter: %d, application counter: %d", sessionCounter.getCount(), applicationCounter.getCount()));
            }
        }));

        addComponent(new Link("Go to the Label View", new ExternalResource("#!" + LabelView.NAME)));
        addComponent(new Link("Go to the Error View. I18n error messages", new ExternalResource("#!" + ErrorView.NAME)));
        addComponent(new Link("Go to the I18N View", new ExternalResource("#!" + I18N.NAME)));
        addComponent(new Link("Go to the UI scoped View", new ExternalResource("#!" + UIScopedView.NAME)));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
    }
}
