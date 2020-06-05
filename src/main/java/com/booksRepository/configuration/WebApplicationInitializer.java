package com.booksRepository.configuration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * This class registers a DispatcherServlet and use Java-based Spring configuration.
 *
 * @author Igor Ivanov
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    /**
     * This method configures the given ServletContext with dispatcher servlet, filters necessary for initializing
     * this web application.
     *
     * @param servletContext - servlet context.
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.register(WebConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

        FilterRegistration.Dynamic hiddenMethodFilter = servletContext.addFilter("httpMethodFilter", new HiddenHttpMethodFilter());
        hiddenMethodFilter.addMappingForUrlPatterns(null, false, "/*");

    }

    /**
     * This method creates and returns the list of configuration classes for root application context.
     *
     * @return the list of configuration classes for root application context.
     */
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationConfiguration.class};
    }

    /**
     * This method creates and returns the list of configuration classes for servlet application context.
     *
     * @return the list of configuration classes for servlet application context.
     */
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{WebConfiguration.class};
    }

    /**
     * This method returns the servlet mapping for the DispatcherServlet.
     *
     * @return the servlet mapping for the DispatcherServlet.
     */
    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}
