package ru.xpoft.vaadin.apache_shiro_sample.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
