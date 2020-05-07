package com.yiyjm.nest.controller;

import com.yiyjm.nest.common.CommonConstants;
import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.config.CrawlerCsdn;
import com.yiyjm.nest.config.CrawlerDytt;
import com.yiyjm.nest.dao.UserDao;
import com.yiyjm.nest.entity.Image;
import com.yiyjm.nest.entity.User;
import com.yiyjm.nest.service.AdminService;
import com.yiyjm.nest.service.BlogService;
import com.yiyjm.nest.service.LocalImgService;
import com.yiyjm.nest.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * admin 控制器
 *
 * @author jonny
 * @date 2020/04/30
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private AdminService adminService;
	private LocalImgService localImgService;
	private BlogService blogService;
	private HttpSession session;
	private UserDao userDao;
	private static final Set<String> LOCALHOST_SET = Stream.of("127.0.0.1", "localhost").collect(Collectors.toSet());

	/**
	 * 后台首页
	 * 判断是否需要登录，如果需要登录，则跳转登录页面，否则进入首页
	 *
	 * @param request HttpServletRequest
	 * @return {@link String}
	 */
	@RequestMapping({"/index.html", "/", ""})
	public String index(HttpServletRequest request, ModelMap modelMap) {
		String servletName = request.getServerName();
		logger.info("servletName：" + servletName);
		// 本地测试，不需要登陆
		if (LOCALHOST_SET.contains(servletName)) {
			session.setAttribute(CommonConstants.LOGIN_SESSION_ID, Config.TOKEN_DO_LOGIN);
			return "admin/index";
		}

		if (!Config.TOKEN_DO_LOGIN.equals(session.getAttribute(CommonConstants.LOGIN_SESSION_ID))) {
			modelMap.put("loginSessionId", Config.TOKEN_DO_LOGIN);
			return "admin/login";
		}
		return "admin/index";
	}

	/**
	 * 登录
	 *
	 * @param map map
	 * @return {@link String}
	 */
	@RequestMapping("/login")
	public String loginPage(ModelMap map) {
		// 如果已经登录，则直接返回后台首页
		if (!Config.TOKEN_DO_LOGIN.equals(session.getAttribute(CommonConstants.LOGIN_SESSION_ID))) {
			return "redirect:/";
		}
		map.put("loginSessionId", Config.TOKEN_DO_LOGIN);
		return "admin/index";
	}


	/**
	 * 做登录
	 *
	 * @param loginSessionId 登录会话id
	 * @param veri           真实
	 * @param username       用户名
	 * @param passwd         passwd
	 * @return {@link String}
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(String loginSessionId, String veri, String username, String passwd) {
		// 获取session中的验证码
		String storeVeri = (String) session.getAttribute("veri");
		User user = userDao.queryByName(username);
		// 验证信息 如果不正确，重新返回登录页面
//		if (!Config.TOKEN_DO_LOGIN.equals(loginSessionId) || !storeVeri.equalsIgnoreCase(veri) || !user.getPasswd().equals(passwd)) {
		if (!Config.TOKEN_DO_LOGIN.equals(loginSessionId) || !user.getPasswd().equals(passwd)) {
			return "redirect:/admin/login";
		}
		// 验证通过 设置 session
		session.setAttribute(CommonConstants.LOGIN_SESSION_ID, Config.TOKEN_DO_LOGIN);
		session.setMaxInactiveInterval(300 * 60);
		return "redirect:/admin";
	}

	/**
	 * 生成验证码
	 *
	 * @param request  请求
	 * @param response 响应
	 */
	@RequestMapping("/veri")
	public void veri(HttpServletRequest request, HttpServletResponse response) {
		try {
			ImageUtil.getVeri(request, response, "veri");
		} catch (IOException e) {
			logger.info("生成验证码错误");
		}
	}

	/**
	 * 图像
	 *
	 * @param map  地图
	 * @param page 页面
	 * @return {@link String}
	 */
	@RequestMapping("/image")
	public String image(ModelMap map, Integer page) {

		if (!Config.TOKEN_DO_LOGIN.equals(session.getAttribute(CommonConstants.LOGIN_SESSION_ID))) {
			return "admin/login";
		}

		if (page == null || page < 1) {
			page = 1;
		}

		int total = localImgService.countImage();
		int number = Config.PAGE_NUMBER;

		int allpage = total % number == 0 ? total / number : total / number + 1;
		if (allpage < 1) {
			allpage = 1;
		}
		List<Image> images = localImgService.listImage(page, number);

		map.put("total", total);
		map.put("allpage", allpage);
		map.put("curpage", page);
		map.put("images", images);
		map.put("ossUrl", Config.LOCAL_URL_PREFIX);
		return "admin/image";
	}

	/**
	 * 根据 bid 编辑博客，bid 没有，创建新的博客
	 *
	 * @param map 地图
	 * @param bid 报价
	 * @return {@link String}
	 */
	@RequestMapping("/blog")
	public String blog(ModelMap map, Integer bid) {
		if (!Config.TOKEN_DO_LOGIN.equals(session.getAttribute(CommonConstants.LOGIN_SESSION_ID))) {
			return "admin/login";
		}
		int bid2 = blogService.gainBlogId(bid);
		if (bid == null || bid < 0) {
			return "redirect:blog?bid=" + bid2;
		}
		map.put("bid", bid2);
		return "admin/blog";
	}

	/**
	 * 博客
	 *
	 * @return {@link String}
	 */
	@RequestMapping("/blogs")
	public String blogs() {
		return "admin/blogs";
	}

	/**
	 * 上传图片
	 *
	 * @param file 文件
	 * @param bid  报价
	 * @return {@link Map<String, Object>}
	 */
	@RequestMapping("/uploadImage")
	@ResponseBody
	public Map<String, Object> uploadImage(@RequestParam("editormd-image-file") MultipartFile file, Integer bid) {
		return localImgService.uploadImage(file, bid, session.getAttribute(CommonConstants.LOGIN_SESSION_ID));
	}

	/**
	 * 删除图片
	 *
	 * @param iid iid
	 * @return {@link String}
	 */
	@RequestMapping("/deleteImage")
	@ResponseBody
	public String deleteImage(Integer iid) {
		return localImgService.deleteImage(iid, session.getAttribute(CommonConstants.LOGIN_SESSION_ID));
	}

	/**
	 * 履带
	 *
	 * @param kind 类
	 * @return {@link String}
	 */
	@RequestMapping("/crawler")
	@ResponseBody
	public String crawler(String kind) {
		if (!Config.TOKEN_DO_LOGIN.equals(session.getAttribute(CommonConstants.LOGIN_SESSION_ID))) {
			return "请先登录";
		}

		if (kind == null) {
			return "类型错误";
		} else if (kind.equals(CommonConstants.CSDN)) {
			adminService.crawler(CommonConstants.CSDN_URL, CrawlerCsdn.class);
		} else if (kind.equals(CommonConstants.DYTT)) {
			adminService.crawler(CommonConstants.DYTT_URL, CrawlerDytt.class);
		} else {
			return "类型错误";
		}

		return "正在爬取中，请查看日志";
	}

	/**
	 * 注销
	 *
	 * @return {@link String}
	 */
	@RequestMapping("/logout")
	public String logout() {
		session.removeAttribute(CommonConstants.LOGIN_SESSION_ID);
		return "redirect:/";
	}

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setLocalImgService(LocalImgService localImgService) {
		this.localImgService = localImgService;
	}

}
