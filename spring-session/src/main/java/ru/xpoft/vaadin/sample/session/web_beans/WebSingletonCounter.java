package ru.xpoft.vaadin.sample.session.web_beans;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xpoft
 */
@Component
public class WebSingletonCounter implements Serializable
{
    private int count = 0;

    public int getCount()
    {
        return ++count;
    }
}
