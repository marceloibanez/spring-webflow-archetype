package ${package}.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import ${package}.entities.MailEntity;
import ${package}.service.MailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.velocity.VelocityEngineUtils;

public abstract class MailServiceImpl<T extends MailEntity> implements MailService<T> {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Async
	public void sendEmail(final T model) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {

				Map<String, T> modelMap = new HashMap<String, T>();
				modelMap.put("dataModel", model);
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(model.getEmail());
				message.setFrom(getFrom());
				message.setSubject(getSubject());

				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, getTemplatePath(), modelMap);

				message.setText(text, true);
			}
		};

		try {
			this.mailSender.send(preparator);
		} catch (Exception e) {
			LOG.error("Error sending email", e);
		}
	}

}
