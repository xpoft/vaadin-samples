package ru.xpoft.vaadin.sample.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.xpoft.vaadin.SpringVaadinServlet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xpoft
 */
@Configuration
@ComponentScan(basePackages = "ru.xpoft.vaadin.sample")
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean()
    {
        ServletRegistrationBean registration = new ServletRegistrationBean(new SpringVaadinServlet());

        // Servlet. init-param
        Map<String, String> params = new HashMap<String, String>();
        params.put("beanName", "myUI");
        registration.setInitParameters(params);

        return registration;
    }
}
