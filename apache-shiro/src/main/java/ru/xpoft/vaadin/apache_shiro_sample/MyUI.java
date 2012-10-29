package ru.xpoft.vaadin.apache_shiro_sample;

import com.vaadin.server.*;
import com.vaadin.ui.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.DiscoveryNavigator;
import ru.xpoft.vaadin.security.ShiroSecurityNavigator;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
public class MyUI extends UI implements Terminal.ErrorListener
{
    @Override
    protected void init(final VaadinRequest request)
    {
        VaadinServiceSession.getCurrent().setErrorHandler(this);
        setSizeFull();

        try
        {
            ShiroSecurityNavigator navigator = new ShiroSecurityNavigator(this, getContent());
            navigator.navigateTo(UI.getCurrent().getPage().getFragment());
        }
        /**
         * Exception on page load
         */
        catch (IllegalArgumentException e)
        {
            Label label = new Label(e.getMessage());
            label.setWidth(-1, Unit.PERCENTAGE);

            Link goToMain = new Link("Go to main", new ExternalResource("/"));

            VerticalLayout layout = new VerticalLayout();
            layout.addComponent(label);
            layout.addComponent(goToMain);
            layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
            layout.setComponentAlignment(goToMain, Alignment.MIDDLE_CENTER);

            VerticalLayout mainLayout = new VerticalLayout();
            mainLayout.setSizeFull();
            mainLayout.addComponent(layout);
            mainLayout.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);

            setContent(mainLayout);
            Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
        }
    }

    /**
     * Exception on action
     */
    @Override
    public void terminalError(Terminal.ErrorEvent event)
    {
        if (event.getThrowable().getCause() instanceof IllegalArgumentException)
        {
            IllegalArgumentException exception = (IllegalArgumentException) event.getThrowable().getCause();
            Notification.show(exception.getMessage(), Notification.Type.ERROR_MESSAGE);
            return;
        }

        DefaultErrorListener.doDefault(event);
    }
}
