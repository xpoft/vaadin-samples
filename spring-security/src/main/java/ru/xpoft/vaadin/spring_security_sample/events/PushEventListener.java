package ru.xpoft.vaadin.spring_security_sample.events;

/**
 * @author xpoft
 */
public interface PushEventListener
{
    public void labelValue(String value, int sendUiId);
}
