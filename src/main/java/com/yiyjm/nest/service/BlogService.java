package com.yiyjm.nest.service;

import com.yiyjm.nest.config.JsonResult;
import com.yiyjm.nest.entity.Blog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 博客服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface BlogService {
	/**
	 * 获得博客id
	 * bid < 1，并且没有草稿博客，新建草稿博客
	 * bid > 0 ，返回该 bid
	 *
	 * @param bid 报价
	 * @return int
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	int gainBlogId(Integer bid);

	/**
	 * get
	 *
	 * @param bid 报价
	 * @return {@link JsonResult<Blog>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	JsonResult<Blog> get(Integer bid);

	/**
	 * 更新
	 *
	 * @param blog       博客
	 * @param adminToken 管理令牌
	 * @return {@link JsonResult<String>}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	JsonResult<String> update(Blog blog, Object adminToken);

	/**
	 * 数
	 *
	 * @param key  关键
	 * @param size 大小
	 * @return int
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	int count(String key, int size);

	/**
	 * 搜索
	 *
	 * @param key  关键
	 * @param page 页面
	 * @return {@link List<Blog>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	List<Blog> search(String key, Integer page);

	/**
	 * get 博客esc
	 *
	 * @param bid 报价
	 * @return {@link Blog}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	Blog getBlogEsc(Integer bid);

	/**
	 * 更换标题esc
	 *
	 * @param blogs 博客
	 * @return {@link List<Blog>}
	 */
	List<Blog> replaceTitleEsc(List<Blog> blogs);

	/**
	 * 兰德
	 *
	 * @param number 数量
	 * @return {@link List<Blog>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	List<Blog> rand(int number);
}
