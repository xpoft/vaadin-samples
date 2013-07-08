package ru.xpoft.vaadin.spring_security_sample.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.VaadinView;
import ru.xpoft.vaadin.spring_security_sample.SpringSecurityHelper;
import ru.xpoft.vaadin.spring_security_sample.beans.SessionCounter;
import ru.xpoft.vaadin.spring_security_sample.beans.PushEvent;
import ru.xpoft.vaadin.spring_security_sample.events.PushEventListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xpoft
 */
@Component
@Scope("prototype")
@VaadinView(value = MainView.NAME, cached = true)
public class MainView extends Panel implements View
{
    @Autowired
    private SessionCounter sessionCounter;

    @Autowired
    private PushEvent pushEvent;

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

        final Label pushLabel = new Label();
        layout.addComponent(pushLabel);

        Button button = new Button("Test button");
        button.addClickListener(new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent event)
            {

                event.getButton().getUI().access(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String string = "sessionCounter.getCount: " + sessionCounter.getCount();
                        pushEvent.sendInfo(string, getUI().getUIId());
                    }
                });
            }
        });

        layout.addComponent(button);

        pushEvent.addListener(new PushEventListener()
        {
            @Override
            public void labelValue(final String value, final int sendUiId)
            {
                getUI().access(new Runnable()
                {
                    public void run()
                    {
                        String UiInfo = (getUI().getUIId() == sendUiId ? "from this UI" : "from another UI");

                        pushLabel.setValue(value + " (" + UiInfo + ")");
                    }
                });
            }
        });

        setContent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event)
    {
        String username = "NULL";
        String roles = "NULL";

        if (SecurityContextHolder.getContext().getAuthentication() != null)
        {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> rolesList = new ArrayList<String>();
            for (GrantedAuthority grantedAuthority : user.getAuthorities())
            {
                rolesList.add(grantedAuthority.getAuthority());
            }

            username = user.getUsername();
            roles = StringUtils.join(rolesList, ",");
        }

        usernameLabel.setValue(username);
        rolesLabel.setValue(roles);
    }
}
