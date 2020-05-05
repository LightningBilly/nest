package com.yiyjm.nest.service;

import com.yiyjm.nest.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 本地img服务
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Service
public interface LocalImgService {

	/**
	 * 上传图片
	 *
	 * @param file       文件
	 * @param bid        报价
	 * @param adminToken 管理令牌
	 * @return {@link Map<String, Object>}
	 */
	Map<String, Object> uploadImage(MultipartFile file, Integer bid, Object adminToken);

	/**
	 * 删除图片
	 *
	 * @param iid        iid
	 * @param adminToken 管理令牌
	 * @return {@link String}
	 */
	String deleteImage(Integer iid, Object adminToken);

	/**
	 * 图像列表
	 *
	 * @param page   页面
	 * @param number 数量
	 * @return {@link List<Image>}
	 */
	List<Image> listImage(int page, int number);

	/**
	 * 计算图像
	 *
	 * @return int
	 */
	int countImage();


}
