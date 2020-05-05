package com.yiyjm.nest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface AdminService {

	/**
	 * 爬虫
	 *
	 * @param startUrl 开始的url
	 * @param t        t
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	void crawler(String startUrl, Class t);

}
