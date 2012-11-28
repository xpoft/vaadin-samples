package ru.xpoft.vaadin.sample.integration.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinMessageSource;
import ru.xpoft.vaadin.VaadinView;
import ru.xpoft.vaadin.sample.integration.components.ChooseLanguage;

import javax.annotation.PostConstruct;
import java.security.GeneralSecurityException;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
@VaadinView(I18N.NAME)
public class I18N extends Panel implements View
{
    private static Logger logger = LoggerFactory.getLogger(I18N.class);

    public static final String NAME = "i18n";

    @Autowired
    private VaadinMessageSource messageSource;

    @Autowired
    private ChooseLanguage chooseLanguage;

    @PostConstruct
    public void PostConstruct() throws GeneralSecurityException
    {
        setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        layout.addComponent(chooseLanguage);

        layout.addComponent(new Label(messageSource.getMessage("i18n.message1")));
        layout.addComponent(new Button(messageSource.getMessage("i18n.button1"), new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent)
            {
                Notification.show(messageSource.getMessage("i18n.notification1"));
            }
        }));
        Link link1 = new Link(messageSource.getMessage("i18n.link1"), new ExternalResource("http://vaadin.com/addon/springvaadinintegration"));
        link1.setTargetName("_blank");
        layout.addComponent(link1);

        layout.addComponent(new Button(messageSource.getMessage("main.go_back"), new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                Page.getCurrent().setUriFragment("!" + MainView.NAME);
            }
        }));

        setContent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {
    }
}
