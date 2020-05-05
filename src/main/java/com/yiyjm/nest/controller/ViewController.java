package com.yiyjm.nest.controller;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.entity.Blog;
import com.yiyjm.nest.service.BlogService;
import com.yiyjm.nest.service.IpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.List;

/**
 * 视图控制器
 *
 * @author jonny
 * @date 2020/04/30
 */
@Controller
public class ViewController {
	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);
	private IpService ipService;
	private BlogService blogService;

	/**
	 * 设置ip服务
	 *
	 * @param ipService 知识产权服务
	 */
	@Autowired
	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	/**
	 * 集博客服务
	 *
	 * @param blogService 博客服务
	 */
	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	/**
	 * 指数
	 *
	 * @return {@link String}
	 */
	@RequestMapping({"/index.html", "/", ""})
	public String index() {
		return "index";
	}

	/**
	 * 聊天
	 *
	 * @return {@link String}
	 */
	@RequestMapping("/chat")
	public String chat() {
		return "pages/chat";
	}

	/**
	 * 瀑布
	 *
	 * @param map 地图
	 * @return {@link String}
	 */
	@RequestMapping("/falls")
	public String falls(ModelMap map) {
		map.put("prefix", Config.LOCAL_URL_PREFIX);
		return "pages/falls";
	}

	/**
	 * 时间轴
	 *
	 * @return {@link String}
	 */
	@RequestMapping("/timeline")
	public String timeline() {
		return "pages/timeline";
	}

	/**
	 * 知识产权
	 *
	 * @param map 地图
	 * @return {@link String}
	 */
	@GetMapping("/ip")
	public String ip(ModelMap map) {
		map.put("ipCount", Config.ipCount);
		map.put("allpage", ipService.getAllPage());
		return "pages/ip";
	}

	/**
	 * 图表
	 *
	 * @return {@link String}
	 */
	@RequestMapping("/chart")
	public String chart() {
		return "pages/chart";
	}

	/**
	 * 博客
	 *
	 * @param map  地图
	 * @param key  关键
	 * @param page 页面
	 * @return {@link String}
	 */
	@RequestMapping("/blogs")
	public String blogs(ModelMap map, String key, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (key != null && key.trim().isEmpty()) {
			key = null;
		}

		List<Blog> blogs = blogService.search(key, page);
		List<Blog> rands = blogService.rand(5);
		int total = blogService.count(key, blogs.size());

		int per = Config.PAGE_NUMBER;
		int allpage = total % per == 0 ? total / per : total / per + 1;
		if (allpage < 1) {
			allpage = 1;
		}

		map.put("key", key);
		map.put("page", page);
		map.put("allpage", allpage);
		map.put("blogs", blogs);
		map.put("rands", rands);
		return "pages/blogs";
	}

	/**
	 * 博客
	 *
	 * @param map 地图
	 * @param bid 报价
	 * @return {@link String}
	 */
	@RequestMapping("/blog/{bid}")
	public String blog(ModelMap map, @PathVariable("bid") Integer bid) {
		Blog blog = blogService.getBlogEsc(bid);
		if (blog == null) {
			blog = new Blog();
			blog.setBid(0);
			blog.setTitle("没有该标题");
			blog.setContent("没有该内容");
			blog.setKeyword("没有关键字");
			blog.setUrl("");
			blog.setRank((byte) (0));
			blog.setModtime(new Timestamp(System.currentTimeMillis()));
		}

		List<Blog> rands = blogService.rand(5);
		map.put("bid", bid);
		map.put("blog", blog);
		map.put("rands", rands);
		return "pages/blog";
	}

}
