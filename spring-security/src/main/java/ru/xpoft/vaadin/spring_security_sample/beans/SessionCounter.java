package ru.xpoft.vaadin.spring_security_sample.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xpoft
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCounter implements Serializable
{
    private int count = 0;

    public int getCount()
    {
        return ++count;
    }
}
