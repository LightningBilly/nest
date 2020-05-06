package com.yiyjm.nest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 相册控制器
 *
 * @author Jonny.Chang
 * @date 2020/05/06
 */
@Controller
public class AblumsController {
	@RequestMapping("ablums/3d")
	public String threePhoto() {
		return "ablums/3d/index";
	}

	@RequestMapping("ablums/ipresenter")
	public String ipresenter() {
		return "ablums/ipresenter/index";
	}

	@RequestMapping("ablums/rotates")
	public String rotates() {
		return "ablums/rotates/index";
	}

}
