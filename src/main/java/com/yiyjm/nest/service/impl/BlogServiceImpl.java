package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.dao.BlogDao;
import com.yiyjm.nest.entity.Blog;
import com.yiyjm.nest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

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
}
