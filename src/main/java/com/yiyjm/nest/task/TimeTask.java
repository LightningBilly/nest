package com.yiyjm.nest.task;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.config.FirstPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * TimeTask
 *
 * @author Jonny.Chang
 * @date 2020/05/01
 */
@Component
public class TimeTask {
	private static final Logger logger = LoggerFactory.getLogger(TimeTask.class);
	private ServletContext servletContext;

	/**
	 * set servletcontext
	 *
	 * @param servletContext
	 */
	@Autowired
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}


	/**
	 * 重置纪念日天数，清空统计缓存
	 * 秒 分 时 日 月 周
	 */
	@Scheduled(cron = "0 0 */6 * * *")
	public void resetDays() {
		// 重置统计
		Config.charts = null;
		// 重置留言个数
		Config.LETTER_TODAY_IP.clear();

		if (servletContext == null) {
			logger.info("重置纪念日终止，未获取到 servletContext");
			return;
		}

		servletContext.setAttribute(Config.SERVLET_FIRST_PAGE, new FirstPage());
		logger.info("已重置纪念日和图表缓存");
	}

}
