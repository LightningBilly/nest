package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.config.JsonResult;
import com.yiyjm.nest.dao.BlogDao;
import com.yiyjm.nest.entity.Blog;
import com.yiyjm.nest.service.BlogService;
import com.yiyjm.nest.util.XssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * 博客服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("blogServiceId")
public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao;

	@Autowired
	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	@Override
	public int gainBlogId(Integer bid) {
		// 如果没有传参
		if (bid == null || bid < 1) {
			// 获取一个草稿
			bid = blogDao.getLastDraft();
			// 如果没有草稿，创建一个草稿
			if (bid == null || bid < 1) {
				Blog blog = new Blog();
				blog.setTitle("无标题");
				blog.setKeyword("");
				blog.setContent("无内容");
				blog.setUrl("");
				blog.setModtime(new Timestamp(System.currentTimeMillis()));
				blog.setRank((byte) -1);
				if (blogDao.insert(blog) > 0) {
					return blog.getBid();
				}
				return 0;
			}
		}
		return bid;
	}

	@Override
	public JsonResult<Blog> get(Integer bid) {
		JsonResult<Blog> result = new JsonResult<>();
		if (bid == null || bid < 1) {
			return result.error(-1, "bid 错误");
		}

		Blog blog = blogDao.get(bid);
		if (blog == null) {
			return result.error(-1, "blog 不存在");
		}

		return result.ok(blog, "ok");
	}

	@Override
	public JsonResult<String> update(Blog blog, Object adminToken) {
		JsonResult<String> result = new JsonResult<>();

		if (blog == null || blog.getBid() == null) {
			return result.error(-1, "内容不存在");
		}

//		// 用户鉴权
//		if (!Config.TOKEN_DO_LOGIN.equals(adminToken)) {
//			return result.error(-1, "没有管理员权限，不能修改");
//		}

		if (blog.getRank() == null) {
			return result.error(-1, "rank 不存在");
		}
		if (blog.getTitle() == null) {
			return result.error(-1, "title 不存在");
		}
		blog.setTitle(blog.getTitle().trim());
		if (blog.getTitle().length() > 200) {
			return result.error(-1, "title 长度>200");
		}
		if (blog.getKeyword() == null) {
			return result.error(-1, "keyword 不存在");
		}

		blog.setKeyword(blog.getKeyword().trim());
		// 多个连续空白，替换成一个 ,
		blog.setKeyword(blog.getKeyword().replaceAll("\\s+", ","));
		// 中文，替换成英文,
		blog.setKeyword(blog.getKeyword().replace("，", ","));
		if (blog.getKeyword().length() > 200) {
			return result.error(-1, "keyword 长度>200");
		}
		if (blog.getContent() == null || blog.getContent().length() > 15000) {
			return result.error(-1, "content 不存在或长度>15000");
		}
		blog.setModtime(new Timestamp(System.currentTimeMillis()));

		blogDao.update(blog);
		return result.ok("ok", "ok");
	}

	@Override
	public int count(String key, int size) {
		if (key == null || key.length() < 2) {
			return blogDao.countAll();
		} else if (size >= Config.PAGE_NUMBER) {
			return Config.PAGE_NUMBER * 5;
		} else {
			return Config.PAGE_NUMBER;
		}
	}

	@Override
	public List<Blog> search(String key, Integer page) {
		int per = Config.PAGE_NUMBER;
		List<Blog> blogs;
		if (key == null || key.length() < 2) {
			blogs = blogDao.listByPage((page - 1) * per, per);
		} else {
			blogs = blogDao.searchByKey2(key, (page - 1) * per, per);
		}

		return replaceTitleEsc(blogs);
	}

	@Override
	public Blog getBlogEsc(Integer bid) {
		if (bid == null || bid <= 0) {
			return null;
		}
		Blog blog = blogDao.get(bid);
		if (blog == null) {
			return null;
		}

		blog.setTitle(XssUtil.replaceHtmlToEsc(blog.getTitle()));
		blog.setKeyword(XssUtil.replaceHtmlToEsc(blog.getKeyword()));
		blog.setContent(XssUtil.replaceHtmlToEsc(blog.getContent()));
		return blog;
	}

	@Override
	public List<Blog> replaceTitleEsc(List<Blog> blogs) {
		for (Blog blog : blogs) {
			blog.setTitle(XssUtil.replaceHtmlToEsc(blog.getTitle()));
			blog.setKeyword(XssUtil.replaceHtmlToEsc(blog.getKeyword()));
		}
		return blogs;
	}

	@Override
	public List<Blog> rand(int number) {
		List<Blog> rands = blogDao.listRand(number);
		for (Blog blog : rands) {
			blog.setTitle(XssUtil.replaceHtmlToEsc(blog.getTitle()));
		}
		return rands;
	}
}
