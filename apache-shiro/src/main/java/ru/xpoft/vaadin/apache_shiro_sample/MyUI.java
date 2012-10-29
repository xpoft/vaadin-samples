package ru.xpoft.vaadin.apache_shiro_sample;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.security.ShiroSecurityNavigator;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
public class MyUI extends UI
{
    @Override
    protected void init(final VaadinRequest request)
    {
        setSizeFull();

        ShiroSecurityNavigator navigator = new ShiroSecurityNavigator(this, getContent());
        navigator.navigateTo(UI.getCurrent().getPage().getFragment());
    }
}
