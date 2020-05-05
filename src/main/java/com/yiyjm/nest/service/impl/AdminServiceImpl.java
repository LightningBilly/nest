package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.service.AdminService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.stereotype.Component;

/**
 * 管理服务impl
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("adminServiceId")
public class AdminServiceImpl implements AdminService {

	@SuppressWarnings("unchecked")
	@Override
	public void crawler(String startUrl, Class t) {
		CrawlConfig config = new CrawlConfig();
		// 保存位置
		config.setCrawlStorageFolder(Config.CRAWLER_LOCAL_FOLER);
		// 开启 ssl
		config.setIncludeHttpsPages(true);
		// 最大抓几个
		config.setMaxPagesToFetch(Config.CRAWLER_MAX_PAGES_TO_FETCH);
		// 递归深度
		config.setMaxDepthOfCrawling(Config.CRAWLER_MAX_DEPTH_OF_CRAWLING);

		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = null;
		try {
			controller = new CrawlController(config, pageFetcher, robotstxtServer);
		} catch (Exception e) {
			return;
		}

		controller.addSeed(startUrl);
		// 开启1个线程
		controller.start(t, 1);
	}

}
