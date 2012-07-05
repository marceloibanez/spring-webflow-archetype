package ${package}.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = { "${package}.controller" })
@ImportResource(value = "classpath:spring/webflow-config.xml")
public class WebMvcConfig {

}
