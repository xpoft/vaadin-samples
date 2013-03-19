package ru.xpoft.vaadin.apache_shiro_sample.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;
import ru.xpoft.vaadin.security.ShiroSecurityNavigator;

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

    private Label usernameLabel = new Label();
    //private Label rolesLabel = new Label();

    @PostConstruct
    public void PostConstruct()
    {
        setSizeFull();
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        HorizontalLayout usernameLayout = new HorizontalLayout();
        usernameLayout.setSpacing(true);
        usernameLayout.addComponent(new Label("Username:"));
        usernameLayout.addComponent(usernameLabel);

        //HorizontalLayout userRolesLayout = new HorizontalLayout();
        //userRolesLayout.setSpacing(true);
        //userRolesLayout.addComponent(new Label("Roles:"));
        //userRolesLayout.addComponent(rolesLabel);

        layout.addComponent(usernameLayout);
        //addComponent(userRolesLayout);

        Link roleUserView = new Link("Role \"user\" View (disabled, if user doesn't have access)", new ExternalResource("#!" + RoleUserView.NAME));
        Link roleAdminView = new Link("Role \"admin\" View (disabled, if user doesn't have access)", new ExternalResource("#!" + RoleAdminView.NAME));
        Link authenticatedView = new Link("@RequiresAuthentication View (disabled, if user doesn't have access)", new ExternalResource("#!" + AuthenticatedView.NAME));
        Link guestView = new Link("@RequiresGuest View (disabled, if user doesn't have access)", new ExternalResource("#!" + GuestView.NAME));
        Link userView = new Link("@RequiresUser View (disabled, if user doesn't have access)", new ExternalResource("#!" + UserView.NAME));

        roleUserView.setEnabled(ShiroSecurityNavigator.hasAccess(RoleUserView.class));
        roleAdminView.setEnabled(ShiroSecurityNavigator.hasAccess(RoleAdminView.class));
        authenticatedView.setEnabled(ShiroSecurityNavigator.hasAccess(AuthenticatedView.class));
        guestView.setEnabled(ShiroSecurityNavigator.hasAccess(GuestView.class));
        userView.setEnabled(ShiroSecurityNavigator.hasAccess(UserView.class));

        layout.addComponent(roleUserView);
        layout.addComponent(roleAdminView);
        layout.addComponent(authenticatedView);
        layout.addComponent(guestView);
        layout.addComponent(userView);
        layout.addComponent(new Link("Role \"admin\" View (throw exception, if user doesn't have access)", new ExternalResource("#!" + RoleAdminView.NAME)));

        layout.addComponent(new Link("Logout", new ExternalResource("/logout/")));

        setContent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        Subject subject = SecurityUtils.getSubject();

        usernameLabel.setValue((String) subject.getPrincipal());
        //rolesLabel.setValue("");
    }
}
