package com.yiyjm.nest.service;


import com.yiyjm.nest.entity.Chart;
import com.yiyjm.nest.entity.Ip;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 知识产权服务
 * IP 服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface IpService {

	/**
	 * 获取所有页面
	 *
	 * @return int
	 */
	int getAllPage();

	/**
	 * 总数目
	 *
	 * @return int
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	int total();

	/**
	 * ip列表
	 *
	 * @param page 页面
	 * @param per  每
	 * @return {@link List<Ip>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	List<Ip> listIp(Integer page, Integer per);

	/**
	 * 添加ip
	 *
	 * @param ip 知识产权
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void addIp(String ip);

	/**
	 * top5城市列表
	 *
	 * @return {@link List<Chart>}
	 */
	List<Chart> listTop5City();

	/**
	 * 列表top5 isp
	 *
	 * @return {@link List<Chart>}
	 */
	List<Chart> listTop5Isp();

	/**
	 * 最后 5 月
	 *
	 * @param time 时间
	 * @return {@link List<Chart>}
	 */
	List<Chart> listMonth5(Timestamp time);

	/**
	 * 最后 5 天
	 *
	 * @param time 时间
	 * @return {@link List<Chart>}
	 */
	List<Chart> listDay5(Timestamp time);


}
