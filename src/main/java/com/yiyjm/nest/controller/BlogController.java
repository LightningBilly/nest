package com.yiyjm.nest.controller;

import com.yiyjm.nest.config.JsonResult;
import com.yiyjm.nest.entity.Blog;
import com.yiyjm.nest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Blog 控制器
 *
 * @author jonny
 * @date 2020/04/30
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
	private BlogService blogService;
	private HttpSession session;

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@RequestMapping("/get")
	@ResponseBody
	public JsonResult<Blog> get(Integer bid) {
		return blogService.get(bid);
	}

	@RequestMapping("/update")
	@ResponseBody
	public JsonResult<String> update(Blog blog) {
		return blogService.update(blog, session.getAttribute("loginSessionId"));
	}
}
