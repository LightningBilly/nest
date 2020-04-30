package com.yiyjm.nest.service;

import com.yiyjm.nest.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * MailService
 *
 * @author jonny
 * @date 2020/05/01
 */
@Service
public class MailService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String mailFrom;

	/**
	 * set javamailsender
	 *
	 * @param javaMailSender
	 */
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * 发送邮件耗时任务，另起线程，不影响主线程
	 *
	 * @param from
	 * @param to
	 * @param title
	 * @param text
	 */
	private void sendSimpleMail(String from, String to, String title, String text) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setFrom(from);      // 发送人
					mailMessage.setTo(to);          // 接收人
					mailMessage.setSubject(title);
					mailMessage.setText(text);

					javaMailSender.send(mailMessage);
				} catch (Exception e) {
					logger.error("发送邮件失败");
				}
			}
		}).start();
	}

	/**
	 * sendMail
	 *
	 * @param title
	 * @param text
	 */
	public void sendMail(String title, String text) {
		if (mailFrom == null || mailFrom.isEmpty()) {
			logger.info("邮件发送者未配置，不发送邮件！");
			return;
		}
		for (String mailTo : Config.ADMIN_MAIL) {
			sendSimpleMail(mailFrom, mailTo, title, text);
		}
	}

}
