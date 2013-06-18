package ru.xpoft.vaadin.spring_security_sample.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;
import ru.xpoft.vaadin.spring_security_sample.SpringSecurityHelper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
    private Label rolesLabel = new Label();

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

        HorizontalLayout userRolesLayout = new HorizontalLayout();
        userRolesLayout.setSpacing(true);
        userRolesLayout.addComponent(new Label("Roles:"));
        userRolesLayout.addComponent(rolesLabel);

        layout.addComponent(usernameLayout);
        layout.addComponent(userRolesLayout);

        Link userView = new Link("ROLE_USER View (disabled, if user doesn't have access)", new ExternalResource("#!" + RoleUserView.NAME));
        Link roleView = new Link("ROLE_ADMIN View (disabled, if user doesn't have access)", new ExternalResource("#!" + RoleAdminView.NAME));

        userView.setEnabled(SpringSecurityHelper.hasRole("ROLE_USER"));
        roleView.setEnabled(SpringSecurityHelper.hasRole("ROLE_ADMIN"));

        layout.addComponent(userView);
        layout.addComponent(roleView);
        layout.addComponent(new Link("ROLE_ADMIN View (throw exception, if user doesn't have access)", new ExternalResource("#!" + RoleAdminView.NAME)));

        layout.addComponent(new Link("Logout", new ExternalResource("/j_spring_security_logout")));

        setContent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : user.getAuthorities())
        {
            roles.add(grantedAuthority.getAuthority());
        }

        usernameLabel.setValue(user.getUsername());
        rolesLabel.setValue(StringUtils.join(roles, ","));
    }
}
