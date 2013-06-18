package ru.xpoft.vaadin.sample.integration.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinMessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
public class ChooseLanguage extends Panel
{
    private static Logger logger = LoggerFactory.getLogger(ChooseLanguage.class);

    @Autowired
    private VaadinMessageSource messageSource;

    @PostConstruct
    public void PostConstruct()
    {
        setCaption(messageSource.getMessage("choose_language.select_lang"));
        setWidth(-1, Unit.PIXELS);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth(-1, Unit.PIXELS);
        buttons.setSpacing(true);

        Button russian = new Button(messageSource.getMessage("choose_language.russian"), new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                UI.getCurrent().getSession().setLocale(new Locale("ru"));

                // Reload page
                UI.getCurrent().getPage().setUriFragment(UI.getCurrent().getPage().getUriFragment() + "/");
            }
        });
        russian.setIcon(new ExternalResource("static/img/ru_flag.png"));
        buttons.addComponent(russian);

        Button english = new Button(messageSource.getMessage("choose_language.english"), new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                UI.getCurrent().getSession().setLocale(Locale.ENGLISH);

                // Reload page
                UI.getCurrent().getPage().setUriFragment(UI.getCurrent().getPage().getUriFragment() + "/");
            }
        });
        english.setIcon(new ExternalResource("static/img/uk_flag.png"));
        buttons.addComponent(english);

        Button german = new Button(messageSource.getMessage("choose_language.german"), new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                UI.getCurrent().getSession().setLocale(Locale.GERMAN);

                // Reload page
                UI.getCurrent().getPage().setUriFragment(UI.getCurrent().getPage().getUriFragment() + "/");
            }
        });
        german.setIcon(new ExternalResource("static/img/de_flag.png"));
        buttons.addComponent(german);

        Button finnish = new Button(messageSource.getMessage("choose_language.finnish"), new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                UI.getCurrent().getSession().setLocale(new Locale("fi"));

                // Reload page
                UI.getCurrent().getPage().setUriFragment(UI.getCurrent().getPage().getUriFragment() + "/");
            }
        });
        finnish.setIcon(new ExternalResource("static/img/fi_flag.png"));
        buttons.addComponent(finnish);

        setContent(buttons);
    }
}
