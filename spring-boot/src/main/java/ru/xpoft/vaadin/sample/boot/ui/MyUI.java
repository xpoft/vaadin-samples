package ru.xpoft.vaadin.sample.boot.ui;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.DiscoveryNavigator;
import ru.xpoft.vaadin.sample.boot.beans.ApplicationCounter;
import ru.xpoft.vaadin.sample.boot.beans.SessionCounter;
import ru.xpoft.vaadin.sample.boot.service.SampleService;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
public class MyUI extends UI
{
    @Autowired
    private transient ApplicationContext applicationContext;

    @Autowired
    private SessionCounter sessionCounter;

    @Autowired
    private ApplicationCounter applicationCounter;

    @Autowired
    private SampleService sampleService;

    @Override
    protected void init(final VaadinRequest request)
    {
        setSizeFull();

        DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);

        Notification.show(String.format("Session counter: %d, application counter: %d, sampleService: %d", sessionCounter.getCount(), applicationCounter.getCount(), sampleService.getRandom()));
    }
}
