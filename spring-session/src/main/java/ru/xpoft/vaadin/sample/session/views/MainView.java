package ru.xpoft.vaadin.sample.session.views;

import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;
import ru.xpoft.vaadin.sample.session.MyUI;
import ru.xpoft.vaadin.sample.session.beans.SingletonCounter;
import ru.xpoft.vaadin.sample.session.web_beans.WebSingletonCounter;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
@VaadinView(MainView.NAME)
public class MainView extends Panel implements View
{
    public static final String NAME = "";

    private Label randomIdLabel;
    private Label sessionHashLabel;
    private TextField textField;
    private TextArea textArea;
    private Label pushLabel;

    @Autowired
    private transient ApplicationContext applicationContext;

    @PostConstruct
    public void PostConstruct()
    {
        randomIdLabel = new Label();
        sessionHashLabel = new Label();
        textArea = new TextArea();
        textField = new TextField();
        textField.setImmediate(true);
        textField.setCaption("Text field");
        textField.addTextChangeListener(new FieldEvents.TextChangeListener()
        {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event)
            {
                textArea.setValue(textArea.getValue() + "\n" + event.getText());
            }
        });

        final VerticalLayout sessionInformationLayout = new VerticalLayout();
        sessionInformationLayout.setSpacing(true);
        sessionInformationLayout.setMargin(true);
        sessionInformationLayout.addComponent(randomIdLabel);
        sessionInformationLayout.addComponent(sessionHashLabel);

        final Panel sessionInformation = new Panel();
        sessionInformation.setCaption("Session information");
        sessionInformation.setContent(sessionInformationLayout);
        sessionInformation.setVisible(false);

        // adding a placeholder label, changed afterwards.
        pushLabel = new Label("Push label");

        setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        layout.addComponent(new Label("Session scoped UI hasn't supported anymore :-("));
        layout.addComponent(new Label("It's a label!"));
        layout.addComponent(sessionInformation);
        layout.addComponent(textField);
        layout.addComponent(pushLabel);

        layout.addComponent(new Button("Show session information", new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                long randomId = ((MyUI)MainView.this.getUI()).getRandomId();
                randomIdLabel.setValue("Random UI number: " + randomId);
                sessionHashLabel.setValue("VaadinSession hash: " + VaadinSession.getCurrent().hashCode());

                event.getButton().setVisible(false);
                sessionInformation.setVisible(true);
            }
        }));

        layout.addComponent(new Button("Show couters info", new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {
                String notification = "Current context. "
                        + "SingletonCounter definition: "
                        + applicationContext.containsBeanDefinition("singletonCounter")
                        + ", WebSingletonCounter definition: "
                        + applicationContext.containsBeanDefinition("webSingletonCounter") + "\n"
                        + "Current context. "
                        + "SingletonCounter instance: "
                        + applicationContext.getBean(SingletonCounter.class)
                        + ", WebSingletonCounter instance: "
                        + applicationContext.getBean(WebSingletonCounter.class) + "\n";

                if (applicationContext.getParent() != null)
                {
                    notification += "Parent context. "
                            + "SingletonCounter definition: "
                            + applicationContext.getParent().containsBeanDefinition("singletonCounter")
                            + ", WebSingletonCounter definition: "
                            + applicationContext.getParent().containsBeanDefinition("webSingletonCounter");
                }
                else
                {
                    notification += "Parent context is NULL";
                }
                Notification.show(notification);
            }
        }));

        layout.addComponent(textArea);

        setContent(layout);
    }

    @Override
    public void attach()
    {
        super.attach();

        final UI ui = this.getUI();
        final AtomicInteger pushCounts = new AtomicInteger(0);
        final Runnable beeper = new Runnable()
        {
            public void run()
            {
                ui.access(new Runnable()
                {
                    public void run()
                    {
                        Date date = java.util.Calendar.getInstance().getTime();
                        String text = date.toString() + ". Count: " + pushCounts.incrementAndGet();
                        System.out.println(text);
                        pushLabel.setValue(text);
                    }
                });

            }
        };
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(beeper, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
    }
}
