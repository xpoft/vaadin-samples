package ru.xpoft.vaadin.sample.session;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.DiscoveryNavigator;

import java.util.Random;

/**
 * @author xpoft
 */
@Component
@Scope("session")
public class MyUI extends UI
{
    private long randomId;

    @Override
    protected void init(final VaadinRequest request)
    {
        randomId = new Random().nextLong();

        setSizeFull();

        DiscoveryNavigator navigator = new DiscoveryNavigator(this, this);
    }

    public long getRandomId()
    {
        return randomId;
    }
}