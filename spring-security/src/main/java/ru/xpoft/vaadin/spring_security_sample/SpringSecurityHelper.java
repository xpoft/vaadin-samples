package ru.xpoft.vaadin.spring_security_sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author xpoft
 */
public class SpringSecurityHelper
{
    private static Logger logger = LoggerFactory.getLogger(SpringSecurityHelper.class);

    public static boolean hasRole(String role)
    {
        if (SecurityContextHolder.getContext().getAuthentication() == null)
        {
            return false;
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }
}
