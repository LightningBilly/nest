package com.yiyjm.nest.controller;

import com.yiyjm.nest.config.JsonResult;
import com.yiyjm.nest.entity.Letter;
import com.yiyjm.nest.service.LetterService;
import com.yiyjm.nest.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Letter 控制器
 *
 * @author jonny
 * @date 2020/04/30
 */
@Controller
@RequestMapping("/letter")
public class LetterController {
	private LetterService letterService;

	/**
	 * 集信服务
	 *
	 * @param letterService 信服务
	 */
	@Autowired
	public void setLetterService(LetterService letterService) {
		this.letterService = letterService;
	}

	/**
	 * 倒叙 或 随机 列出 letter
	 *
	 * @param lid    盖子
	 * @param number 数量
	 * @param isRand 是兰德
	 * @return {@link List<Letter>}
	 */
	@PostMapping("/listLetter")
	@ResponseBody
	public List<Letter> listLetter(Integer lid, Integer number, Boolean isRand) {
		List<Letter> letters = letterService.listLetter(lid, number, isRand);
		return letters;
	}

	/**
	 * 插入一个新的留言
	 *
	 * @param request  请求
	 * @param nickname 昵称
	 * @param content  内容
	 * @return {@link JsonResult<String>}
	 */
	@PostMapping("/insertLetter")
	@ResponseBody
	public JsonResult<String> insertLetter(HttpServletRequest request, String nickname, String content) {
		String ip = IpUtil.getIp(request);
		return letterService.insertLetter(ip, nickname, content);
	}

	/**
	 * 赞留言
	 *
	 * @param lid 盖子
	 * @return {@link String}
	 */
	@PostMapping("/zanLetter")
	@ResponseBody
	public String zanLetter(Integer lid) {
		letterService.zanLetter(lid);
		return "ok";
	}
}
