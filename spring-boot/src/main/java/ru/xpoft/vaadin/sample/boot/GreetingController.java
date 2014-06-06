package ru.xpoft.vaadin.sample.boot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xpoft
 */
@RestController
class GreetingController {

    @RequestMapping("/")
    public String hello() {
        return "Hello!";
    }
}
