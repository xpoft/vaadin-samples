package ru.xpoft.vaadin.sample.integration.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xpoft
 */
@Service
public class SampleService
{
    public long getRandom()
    {
        return new Random().nextLong();
    }
}
