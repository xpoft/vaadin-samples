package ru.xpoft.vaadin.spring_security_sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xpoft
 */
@Controller
@RequestMapping("/")
public class LoginController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", defaultValue = "false", required = false)Boolean isError, ModelMap model)
    {
        if (isError)
        {
            model.put("isError", isError);
        }

        return "login";
    }
}
