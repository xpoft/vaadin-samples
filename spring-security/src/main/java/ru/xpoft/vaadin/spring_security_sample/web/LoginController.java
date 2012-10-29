package ru.xpoft.vaadin.spring_security_sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xpoft
 */
@Controller
@RequestMapping("/")
public class LoginController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }
}
