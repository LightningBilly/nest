package com.yiyjm.nest.service;

import com.yiyjm.nest.common.CommonConstants;
import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.entity.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.sql.Timestamp;
import java.util.*;

/**
 * 公共服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public class CommonService {

	private IpService ipService;

	@Autowired
	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}


	/**
	 * 防刷消息
	 *
	 * @param session 会话
	 * @return boolean
	 */
	public synchronized boolean isSafe(Session session) {
		// 去掉超过3秒未说话的，不是灌水的
		long nowTime = System.currentTimeMillis();
		Iterator<Map.Entry<String, Long>> iterator = Config.RECORD_CHAT_ID.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Long> next = iterator.next();
			Long oldtime = next.getValue();
			if (nowTime - oldtime > 3000) {
				iterator.remove();
			}
		}

		// 检测当前的id
		Long oldtime = Config.RECORD_CHAT_ID.get(session.getId());
		// 如果没有记录过Ip，表示没有操作过，放行。否则是1s内操作过，阻止。
		if (oldtime == null) {
			Config.RECORD_CHAT_ID.put(session.getId(), System.currentTimeMillis());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取统计数据，优先使用缓存，task 中定时清除缓存
	 *
	 * @return {@link Map <String, Object>}
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

		List<Chart> citys = ipService.listTop5City();
		for (Chart chart : citys) {
			cityName.add(chart.getName());
			cityNum.add(chart.getNum());
		}
		charts.put("cityName", cityName);
		charts.put("cityNum", cityNum);

		List<Chart> isps = ipService.listTop5Isp();
		for (Chart chart : isps) {
			ispName.add(chart.getName());
			ispNum.add(chart.getNum());
		}
		charts.put("ispName", ispName);
		charts.put("ispNum", ispNum);

		// 统计前 183 天的中，前6个月的，提高效率
		long halfYearAgo = System.currentTimeMillis() - 15811200000L;
		Timestamp timestamp = new Timestamp(halfYearAgo);
		List<Chart> month5 = ipService.listMonth5(timestamp);
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
		List<Chart> day5 = ipService.listDay5(timestamp);
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
	 * 判断是否为数字
	 *
	 * @param str str
	 * @return boolean
	 */
	public boolean isNumber(String str) {
		if (str == null || str.equals(CommonConstants.BLAN)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
