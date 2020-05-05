package com.yiyjm.nest.controller;

import com.yiyjm.nest.entity.Ip;
import com.yiyjm.nest.service.CommonService;
import com.yiyjm.nest.service.IpService;
import com.yiyjm.nest.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * ip 控制器
 *
 * @author jonny
 * @date 2020/04/30
 */
@Controller
@RequestMapping("/ip")
public class IpController {
	private IpService ipService;
	private CommonService commonService;

	/**
	 * 添加ip
	 *
	 * @param request 请求
	 * @return {@link String}
	 */
	@PostMapping("/addIp")
	@ResponseBody
	public String addIp(HttpServletRequest request) {
		String ip = IpUtil.getIp(request);
		ipService.addIp(ip);
		return "ok";
	}

	/**
	 * 表静态
	 *
	 * @return {@link Map<String, Object>}
	 */
	@PostMapping("/chartStatic")
	@ResponseBody
	public Map<String, Object> chartStatic() {
		return commonService.chartStatic();
	}

	/**
	 * ip列表
	 *
	 * @param page 页面
	 * @param per  每
	 * @return {@link List<Ip>}
	 */
	@PostMapping("/listIp")
	@ResponseBody
	public List<Ip> listIp(Integer page, Integer per) {
		return ipService.listIp(page, per);
	}
	@Autowired
	public void setIpService(IpService ipService) {
		this.ipService = ipService;
	}

	@Autowired
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

}
