package com.yiyjm.nest.tools;

import com.yiyjm.nest.dao.ImageDao;
import com.yiyjm.nest.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * image 服务
 *
 * @author jonny
 * @date 2020/04/30
 */
@Service
public class ImageService {
	private ImageDao imageDao;

	/**
	 * set ImageDao
	 *
	 * @param imageDao ImageDao
	 */
	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	/**
	 * 列表
	 *
	 * @param iid iid
	 * @param per per
	 * @return {@link List<Image>}
	 */
	public List<Image> list(Integer iid, Integer per) {
		if (iid == null || iid <= 0) {
			iid = Integer.MAX_VALUE;
		}
		if (per == null || per > 16) {
			per = 16;
		}
		return imageDao.listUploadImage(iid, per);
	}

}
