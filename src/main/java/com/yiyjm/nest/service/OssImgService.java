package com.yiyjm.nest.service;


import com.yiyjm.nest.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * oss img服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("ossImgServiceId")
public interface OssImgService {
	/**
	 * 删除图片
	 *
	 * @param iid        iid
	 * @param adminToken 管理令牌
	 * @return {@link String}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	String deleteImage(Integer iid, Object adminToken);

	/**
	 * 上传图片
	 *
	 * @param file       文件
	 * @param bid        报价
	 * @param adminToken 管理令牌
	 * @return {@link Map <String, Object>}
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	Map<String, Object> uploadImage(MultipartFile file, Integer bid, Object adminToken);

	/**
	 * 统计图像
	 *
	 * @return int
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	int countImage();

	/**
	 * 图像列表
	 *
	 * @param page   页面
	 * @param number 数量
	 * @return {@link List < Image >}
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	List<Image> listImage(int page, int number);
}
