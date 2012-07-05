package ${package}.service.impl;

import ${package}.entities.security.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("mailConfirmationService")
public class UserConfirmationMailServiceImpl extends MailServiceImpl<User>{
	
	@Value("${mail.userconfirm.from}")
	private String from;

	@Value("${mail.userconfirm.subject}")
	private String subject;

	@Override
	public String getTemplatePath() {
		return "mailTemplates/userConfirmEmailTemplate.html";
	}

	@Override
	public String getFrom() {
		return from;
	}

	@Override
	public String getSubject() {
		return subject;
	}
}
