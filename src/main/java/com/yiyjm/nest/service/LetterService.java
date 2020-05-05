package com.yiyjm.nest.service;

import com.yiyjm.nest.config.JsonResult;
import com.yiyjm.nest.entity.Letter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 信服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface LetterService {
	/**
	 * 列表的信
	 *
	 * @param lid    盖子
	 * @param number 数量
	 * @param isRand 是兰德
	 * @return {@link List<Letter>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	List<Letter> listLetter(Integer lid, Integer number, Boolean isRand);

	/**
	 * 插入的信
	 *
	 * @param ip       知识产权
	 * @param nickname 昵称
	 * @param content  内容
	 * @return {@link JsonResult<String>}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	JsonResult<String> insertLetter(String ip, String nickname, String content);

	/**
	 * Letter 点赞
	 *
	 * @param lid 盖子
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void zanLetter(int lid);

}
