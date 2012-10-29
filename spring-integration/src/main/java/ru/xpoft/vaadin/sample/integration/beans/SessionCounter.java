package ru.xpoft.vaadin.sample.integration.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xpoft
 */
@Component
@Scope("session")
public class SessionCounter implements Serializable
{
    private int count = 0;

    public int getCount()
    {
        return ++count;
    }
}
