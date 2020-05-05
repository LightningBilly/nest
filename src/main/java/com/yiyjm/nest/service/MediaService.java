package com.yiyjm.nest.service;

import com.yiyjm.nest.entity.Image;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface MediaService {

	/**
	 * 图片列表
	 *
	 * @param iid iid
	 * @param per per
	 * @return {@link List<Image>}
	 */
	List<Image> imagesList(Integer iid, Integer per);

}
