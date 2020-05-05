package com.yiyjm.nest.service;

import com.yiyjm.nest.entity.Chat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 聊天服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface ChatService {

	/**
	 * 插入消息
	 *
	 * @param object 对象
	 * @return {@link Chat}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	Chat insertMessage(String object);

	/**
	 * 插入机器消息
	 *
	 * @param content 内容
	 * @return {@link Chat}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	Chat insertRobotMessage(String content);

	/**
	 * get 最近消息
	 *
	 * @param max 马克斯
	 * @return {@link String}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	String getRecentMessage(String max);

	/**
	 * 列表
	 *
	 * @param cid    cid
	 * @param number 数量
	 * @return {@link List<Chat>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	List<Chat> list(int cid, int number);

	/**
	 * 是否为数字
	 *
	 * @param str str
	 * @return boolean
	 */
	boolean isNumber(String str);

}
