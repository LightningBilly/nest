package com.yiyjm.nest.controller;

import com.yiyjm.nest.entity.Image;
import com.yiyjm.nest.service.MediaService;
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
	private MediaService mediaService;

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
		return mediaService.imagesList(iid, per);
	}

	@Autowired
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
}
