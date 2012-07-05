package ${package}.service;

public interface MailService<T> {

	void sendEmail(T model);
	
	String getTemplatePath();
	
	String getFrom();
	
	String getSubject();
}
