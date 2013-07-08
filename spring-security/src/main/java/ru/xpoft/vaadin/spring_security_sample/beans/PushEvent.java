package ru.xpoft.vaadin.spring_security_sample.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.xpoft.vaadin.spring_security_sample.events.PushEventListener;

import java.util.WeakHashMap;

/**
 * @author xpoft
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PushEvent
{
    private WeakHashMap<Integer, PushEventListener> listeners = new WeakHashMap<Integer, PushEventListener>();
    private int inx = 0;

    public void addListener(PushEventListener listener)
    {
        listeners.put(inx++, listener);
    }

    public void sendInfo(String value, int sendUiId)
    {
        // Notify everybody that may be interested.
        for (PushEventListener listener : listeners.values())
        {
            listener.labelValue(value, sendUiId);
        }
    }
}
