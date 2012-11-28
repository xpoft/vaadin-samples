package ru.xpoft.vaadin.apache_shiro_sample.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;

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

        Link userView = new Link("Role \"user\" View (disabled, if user doesn't have access)", new ExternalResource("#!" + RoleUserView.NAME));
        Link roleView = new Link("Role \"admin\" View (disabled, if user doesn't have access)", new ExternalResource("#!" + RoleAdminView.NAME));

        userView.setEnabled(SecurityUtils.getSubject().hasRole("user"));
        roleView.setEnabled(SecurityUtils.getSubject().hasRole("admin"));

        layout.addComponent(userView);
        layout.addComponent(roleView);
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
