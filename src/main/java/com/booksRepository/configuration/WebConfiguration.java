package com.booksRepository.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * This class is implementation {@link WebMvcConfigurer}. It configure Spring MVC.
 *
 * @author Igor Ivanov
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.booksRepository.controllers")
public class WebConfiguration implements WebMvcConfigurer
{
    /**
     * This method adds static resources handler.
     *
     * @param registry - resource registry handler.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * This method creates view resolver.
     *
     * @return view resolver.
     */
    @Bean
    public InternalResourceViewResolver jspViewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
