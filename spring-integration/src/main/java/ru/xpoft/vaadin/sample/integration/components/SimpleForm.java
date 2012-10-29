package ru.xpoft.vaadin.sample.integration.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.springframework.context.annotation.Scope;
import ru.xpoft.vaadin.sample.integration.beans.User;

import javax.annotation.PostConstruct;

/**
 * @author xpoft
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class SimpleForm extends VerticalLayout
{
    private static final String COMMON_FIELD_WIDTH = "12em";

    @PostConstruct
    public void PostConstruct()
    {
        User user = new User();

        setCaption("Simple form");
        setSpacing(true);

        TextField name = new TextField("Name");
        name.setRequired(true);
        name.setRequiredError("Please enter a Name!");
        name.setWidth(COMMON_FIELD_WIDTH);
        name.addValidator(new StringLengthValidator("It must be 3-25 characters", 3, 25, false));
        name.setNullRepresentation("");
        name.setImmediate(true);

        TextField email = new TextField("Email:");
        email.setRequired(true);
        email.setRequiredError("Please enter a Email");
        email.setWidth(COMMON_FIELD_WIDTH);
        email.addValidator(new EmailValidator("Not valid email"));
        email.setNullRepresentation("");
        email.setImmediate(true);

        final BeanFieldGroup<User> fieldGroup = new BeanFieldGroup<User>(User.class);
        fieldGroup.setItemDataSource(new BeanItem<User>(user));
        fieldGroup.bind(name, "name");
        fieldGroup.bind(email, "email");

        // The cancel / apply buttons
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        Button discardChanges = new Button("Reset",
                new Button.ClickListener()
                {
                    public void buttonClick(Button.ClickEvent event)
                    {
                        fieldGroup.discard();
                    }
                });
        buttons.addComponent(discardChanges);
        buttons.setComponentAlignment(discardChanges, Alignment.MIDDLE_LEFT);

        Button apply = new Button("Save", new Button.ClickListener()
        {
            public void buttonClick(Button.ClickEvent event)
            {
                try
                {
                    fieldGroup.commit();
                    Notification.show("OK");
                }
                catch (Exception e)
                {
                    Notification.show("Error: " + e.getMessage(), Notification.Type.WARNING_MESSAGE);
                }
            }
        });
        buttons.addComponent(apply);

        addComponent(name);
        addComponent(email);
        addComponent(buttons);
    }
}
