package com.yiyjm.nest.service;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.dao.IpDao;
import com.yiyjm.nest.entity.Chart;
import com.yiyjm.nest.entity.Ip;
import com.yiyjm.nest.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * 知识产权服务
 *
 * @author jonny
 * @date 2020/04/30
 */
@Service
public class IpService {
	private static final Logger logger = LoggerFactory.getLogger(IpService.class);
	private IpDao ipDao;

	/**
	 * set 知识产权 Dao
	 *
	 * @param ipDao 知识产权 Dao
	 */
	@Autowired
	public void setIpDao(IpDao ipDao) {
		this.ipDao = ipDao;
	}

	/**
	 * get 所有page
	 *
	 * @return int
	 */
	public int getAllPage() {
		int allpage = Config.ipCount % 10 == 0 ? Config.ipCount / 10 : Config.ipCount / 10 + 1;
		if (allpage < 1) {
			allpage = 1;
		}
		return allpage;
	}

	/**
	 * 总
	 *
	 * @return int
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int total() {
		return ipDao.total();
	}

	/**
	 * 获取统计数据，优先使用缓存，task 中定时清除缓存
	 *
	 * @return {@link Map<String, Object>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public synchronized Map<String, Object> chartStatic() {
		Map<String, Object> charts = Config.charts;
		if (charts == null) {
			charts = new HashMap<>();
		} else {
			return charts;
		}

		List<String> cityName = new ArrayList<>();
		List<String> ispName = new ArrayList<>();
		List<String> monthName = new ArrayList<>();
		List<String> daysName = new ArrayList<>();

		List<Integer> cityNum = new ArrayList<>();
		List<Integer> ispNum = new ArrayList<>();
		List<Integer> monthNum = new ArrayList<>();
		List<Integer> daysNum = new ArrayList<>();

		List<Chart> citys = ipDao.listTop5City();
		for (Chart chart : citys) {
			cityName.add(chart.getName());
			cityNum.add(chart.getNum());
		}
		charts.put("cityName", cityName);
		charts.put("cityNum", cityNum);

		List<Chart> isps = ipDao.listTop5Isp();
		for (Chart chart : isps) {
			ispName.add(chart.getName());
			ispNum.add(chart.getNum());
		}
		charts.put("ispName", ispName);
		charts.put("ispNum", ispNum);

		// 统计前 183 天的中，前6个月的，提高效率
		long halfYearAgo = System.currentTimeMillis() - 15811200000L;
		Timestamp timestamp = new Timestamp(halfYearAgo);
		List<Chart> month5 = ipDao.listMonth5(timestamp);
		Collections.reverse(month5);
		for (Chart chart : month5) {
			monthName.add(chart.getName());
			monthNum.add(chart.getNum());
		}
		charts.put("monthName", monthName);
		charts.put("monthNum", monthNum);

		// 统计前 14 天中，前5天的，提高效率
		long day14age = System.currentTimeMillis() - 1209600000;
		timestamp = new Timestamp(day14age);
		List<Chart> day5 = ipDao.listDay5(timestamp);
		Collections.reverse(day5);
		for (Chart chart : day5) {
			daysName.add(chart.getName());
			daysNum.add(chart.getNum());
		}
		charts.put("daysName", daysName);
		charts.put("daysNum", daysNum);

		// 进行缓存
		Config.charts = charts;
		return charts;
	}

	/**
	 * @param page 页面
	 * @param per  每
	 * @return {@link List<Ip>}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Ip> listIp(Integer page, Integer per) {
		if (page == null) {
			page = 1;
		}
		if (per == null) {
			per = 10;
		}
		if (per > 32) {
			per = 32;
		}
		if (per < 1) {
			per = 1;
		}
		if (page < 1) {
			page = 1;
		}

		return ipDao.listIp((page - 1) * per, per);
	}

	/**
	 * @param ip 知识产权
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addIp(String ip) {
		// 排除本地 ip
		if (Config.RECORD_IP_IGNORE.contains(ip)) {
			return;
		}

		if (!IpUtil.isSafeIp(ip)) {
			return;
		}

		Ip realIp = IpUtil.getIpAddrByIpIp(ip);
		if (realIp == null) {
			realIp = IpUtil.getIpAddrByAli(ip);
			if (realIp == null) {
				logger.error("ipip ali 未获取到ip信息：null");
				return;
			}
		}

		ipDao.insertIp(realIp);
		Config.ipCount++;
	}

}
