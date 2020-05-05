package com.yiyjm.nest.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yiyjm.nest.common.CommonConstants;
import com.yiyjm.nest.dao.ChatDao;
import com.yiyjm.nest.entity.Chat;
import com.yiyjm.nest.service.ChatService;
import com.yiyjm.nest.util.Jackson;
import com.yiyjm.nest.util.RobotUtil;
import com.yiyjm.nest.util.XssUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

/**
 * 聊天服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("chatServiceId")
public class ChatServiceImpl implements ChatService {

	private static final Logger logger = LoggerFactory.getLogger(com.yiyjm.nest.service.ChatService.class);
	private ChatDao chatDao;

	@Autowired
	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}

	@Override
	public Chat insertMessage(String object) {
		Chat message = null;
		try {
			message = Jackson.toObject(object, Chat.class);
		} catch (IOException e) {
			logger.error("聊天string转化为object失败。");
			return null;
		}
		if (message.getContent() == null || message.getContent().trim().equals("")) {
			return null;
		}
		message.setContent(XssUtil.replaceHtmlToFull(message.getContent()));
		if (message.getContent().length() > 512) {
			message.setContent(message.getContent().substring(0, 512));
		}
		if (message.getGender() == null) {
			return null;
		}

		message.setPubtime(new Timestamp(System.currentTimeMillis()));
		chatDao.insertMessage(message);
		return message;
	}

	@Override
	public Chat insertRobotMessage(String content) {
		String text = RobotUtil.getRobotMessage(content);
		if (text == null) {
			text = RobotUtil.getLiliRobotMessage(content);
		}

		if (text == null) {
			logger.error("从api接口获取机器人回复失败。");
			return null;
		}
		text = XssUtil.replaceHtmlToFull(text);
		if (text.length() > 512) {
			text = text.substring(0, 512);
		}

		Chat robotMessage = new Chat();
		robotMessage.setContent(text);
		robotMessage.setGender((byte) 3);
		robotMessage.setPubtime(new Timestamp(System.currentTimeMillis()));

		chatDao.insertMessage(robotMessage);
		return robotMessage;
	}

	@Override
	public String getRecentMessage(String max) {
		int maxtemp = 0;

		try {
			maxtemp = Integer.parseInt(max);
		} catch (Exception e) {
			maxtemp = Integer.MAX_VALUE;
		}
		if (maxtemp <= 0) {
			maxtemp = Integer.MAX_VALUE;
		}

		List<Chat> messages = chatDao.list(maxtemp, 32);
		Collections.reverse(messages);
		String json;
		try {
			json = Jackson.toJson(messages);
		} catch (JsonProcessingException e) {
			logger.error("获取历史聊天记录失败。");
			json = "[]";
		}
		return json;
	}

	@Override
	public List<Chat> list(int cid, int number) {
		if (number > 32) {
			number = 32;
		}

		if (cid <= 0) {
			cid = Integer.MAX_VALUE;
		}

		return chatDao.list(cid, number);
	}

	@Override
	public boolean isNumber(String str) {
		if (str == null || str.equals(CommonConstants.BLAN)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
