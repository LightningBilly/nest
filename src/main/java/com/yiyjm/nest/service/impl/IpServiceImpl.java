package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.dao.IpDao;
import com.yiyjm.nest.entity.Chart;
import com.yiyjm.nest.entity.Ip;
import com.yiyjm.nest.service.IpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * 知识产权服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("ipServiceId")
public class IpServiceImpl implements IpService {

	private static final Logger logger = LoggerFactory.getLogger(com.yiyjm.nest.service.IpService.class);

	private IpDao ipDao;

	@Autowired
	public void setIpDao(IpDao ipDao) {
		this.ipDao = ipDao;
	}

	@Override
	public int getAllPage() {
		int allpage = Config.ipCount % 10 == 0 ? Config.ipCount / 10 : Config.ipCount / 10 + 1;
		if (allpage < 1) {
			allpage = 1;
		}
		return allpage;
	}

	@Override
	public int total() {
		return ipDao.total();
	}

	@Override
	public List<Ip> listIp(Integer page, Integer per) {
		return null;
	}

	@Override
	public void addIp(String ip) {

	}

	@Override
	public List<Chart> listTop5City() {
		return ipDao.listTop5City();
	}

	@Override
	public List<Chart> listTop5Isp() {
		return ipDao.listTop5Isp();
	}

	@Override
	public List<Chart> listMonth5(Timestamp time) {
		return ipDao.listMonth5(time);
	}

	@Override
	public List<Chart> listDay5(Timestamp time) {
		return ipDao.listDay5(time);
	}
}
