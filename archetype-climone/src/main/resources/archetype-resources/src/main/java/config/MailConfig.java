package ${package}.config;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
public class MailConfig {

	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.user}")
	private String user;
	
	@Value("${mail.pwd}")
	private String pwd;
	
	@Value("${mail.port}")
	private int port;
	
	@Value("${mail.protocol}")
	private String protocol;
	
	@Value("${mail.authenticated}")
	private String isAuthenticated;
	
	@Value("${mail.starttlsenable}")
	private String isStarttlsEnable;
	
	@Bean
	public JavaMailSender mailSender(){
		
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		
		javaMailSenderImpl.setProtocol(this.protocol);
		javaMailSenderImpl.setHost(this.host);
		javaMailSenderImpl.setPort(this.port);		
		
		if(Boolean.valueOf(isAuthenticated)){
			javaMailSenderImpl.setUsername(this.user);
			javaMailSenderImpl.setPassword(this.pwd);
			Properties props = new Properties();		
			props.setProperty("mail.smtp.auth", isAuthenticated);
			props.setProperty("mail.smtp.starttls.enable", isStarttlsEnable);
			javaMailSenderImpl.setJavaMailProperties(props);
		}
		

		return javaMailSenderImpl;		
	}
	
	@Bean
	public VelocityEngineFactoryBean velocityEngine(){
		VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();
		Properties velocityProperties = new Properties();
		velocityProperties.setProperty("resource.loader", "class");
		velocityProperties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngineFactoryBean.setVelocityProperties(velocityProperties);
		return velocityEngineFactoryBean;		
	}
	
}
