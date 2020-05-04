package com.yiyjm.nest.controller;

import com.yiyjm.nest.entity.Image;
import com.yiyjm.nest.tools.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Image 控制器
 *
 * @author jonny
 * @date 2020/04/30
 */
@Controller
@RequestMapping("/image")
public class ImageController {
	private ImageService imageService;

	/**
	 * 设置图像服务
	 *
	 * @param imageService 形象服务
	 */
	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * 列表
	 *
	 * @param iid iid
	 * @param per 每
	 * @return {@link List<Image>}
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<Image> list(Integer iid, Integer per) {
		return imageService.list(iid, per);
	}

}
