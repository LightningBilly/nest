package com.yiyjm.nest.service;

import org.springframework.stereotype.Service;

/**
 * 邮件服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface MailService {

	/**
	 * 发送简单的邮件
	 *
	 * @param from  从
	 * @param to    来
	 * @param title 标题
	 * @param text  文本
	 */
	void sendSimpleMail(String from, String to, String title, String text);

	/**
	 * 发送邮件
	 *
	 * @param title 标题
	 * @param text  文本
	 */
	void sendMail(String title, String text);
}
