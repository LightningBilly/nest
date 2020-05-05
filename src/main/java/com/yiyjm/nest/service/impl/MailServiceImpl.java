package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("mailServiceId")
public class MailServiceImpl implements MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String mailFrom;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	@Override
	public void sendSimpleMail(String from, String to, String title, String text) {
		new Thread(() -> {
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
		}).start();

		/*ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
				.setNameFormat("mail-pool-%d").build();
		ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

		singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
		singleThreadPool.shutdown();*/

	}

	@Override
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
