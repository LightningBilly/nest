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
@RequestMapping("/ablums")
public class AblumsController {
	@RequestMapping({"/3d/index.html", "ablums/3d/index", "ablums/3d"})
	public String threePhoto() {
		return "ablums/3d/index";
	}
}
