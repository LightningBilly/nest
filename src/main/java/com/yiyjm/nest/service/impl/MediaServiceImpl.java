package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.dao.ImageDao;
import com.yiyjm.nest.entity.Image;
import com.yiyjm.nest.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 媒体服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("mediaServiceId")
public class MediaServiceImpl implements MediaService {

	private static final int PER_NUM = 16;
	private ImageDao imageDao;

	@Autowired
	private void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	@Override
	public List<Image> imagesList(Integer iid, Integer per) {
		if (iid == null || iid <= 0) {
			iid = Integer.MAX_VALUE;
		}
		if (per == null || per > PER_NUM) {
			per = 16;
		}
		return imageDao.listUploadImage(iid, per);
	}
}
