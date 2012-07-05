package ${package}.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.sun.faces.config.WebConfiguration;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        // encoding filter
        org.springframework.web.filter.CharacterEncodingFilter encodingFilter = new org.springframework.web.filter.CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(false);
        FilterRegistration.Dynamic encodingFilterDinamic = servletContext.addFilter("charEncodingFilter", encodingFilter);
        encodingFilterDinamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // security filter
        org.springframework.web.filter.DelegatingFilterProxy delegatingFilterProxy = new org.springframework.web.filter.DelegatingFilterProxy(
                "springSecurityFilterChain");
        FilterRegistration.Dynamic securityFilterDynamic = servletContext.addFilter("securityFilter", delegatingFilterProxy);
        securityFilterDynamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // webflow
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebMvcConfig.class);
        org.springframework.web.servlet.DispatcherServlet dispatcherServlet = new org.springframework.web.servlet.DispatcherServlet(
                mvcContext);
        ServletRegistration.Dynamic dispatcherServletDynamic = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        dispatcherServletDynamic.setLoadOnStartup(1);
        dispatcherServletDynamic.addMapping("/view/*");

        // faces
        servletContext.setInitParameter(WebConfiguration.WebContextInitParameter.JavaxFacesProjectStage.getQualifiedName(), "Development");
        servletContext.setInitParameter(WebConfiguration.BooleanWebContextInitParameter.FaceletsSkipComments.getQualifiedName(), "true");
        servletContext.setInitParameter(WebConfiguration.WebContextInitParameter.FaceletsLibraries.getQualifiedName(),
                "/WEB-INF/springsecurity.taglib.xml");

        servletContext.setInitParameter("primefaces.skin", "bluesky");
        
    }
}
