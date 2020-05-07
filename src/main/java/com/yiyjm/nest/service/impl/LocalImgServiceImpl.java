package com.yiyjm.nest.service.impl;

import com.yiyjm.nest.config.Config;
import com.yiyjm.nest.config.ConfigI;
import com.yiyjm.nest.dao.ImageDao;
import com.yiyjm.nest.entity.Image;
import com.yiyjm.nest.service.LocalImgService;
import com.yiyjm.nest.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 当地的img服务实现类
 *
 * @author Jonny.Chang
 * @date 2020/05/05
 */
@Component("localImgServiceId")
public class LocalImgServiceImpl implements LocalImgService {

	private static final Logger logger = LoggerFactory.getLogger(com.yiyjm.nest.service.LocalImgService.class);
	private static final String GIF = ".gif";
	private static final String JPG = ".jpg";
	private ImageDao imageDao;

	@Autowired
	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}

	@Override
	public Map<String, Object> uploadImage(MultipartFile file, Integer bid, Object adminToken) {
		HashMap<String, Object> resultMap = new HashMap<>();
		/* success=0 表示失败，=1表示成功 */
		resultMap.put("success", 0);
		if (bid == null) {
			logger.error("上传错误：图片 bid 不存在");
			resultMap.put("message", "上传错误：图片 bid 不存在！");
			return resultMap;
		}
		/* 在项目资源根目录下，分开博客和相册照片的存放位置 */
		String folder = Config.LOCAL_FILE_ROOT_PATH + Config.LOCAL_BLOG_FOLDER;
		if (bid < 0) {
			folder = Config.LOCAL_FILE_ROOT_PATH + Config.LOCAL_PHOTOS_FOLDER;
		}
		/* 用户鉴权 */
		if (!Config.TOKEN_DO_LOGIN.equals(adminToken)) {
			logger.error("上传错误：管理员token错误");
			resultMap.put("message", "上传错误：管理员token错误");
			return resultMap;
		}
		/* 是否为有效文件 */
		if (file == null || file.isEmpty()) {
			logger.error("上传错误：文件为空");
			resultMap.put("message", "上传错误：文件为空");
			return resultMap;
		}
		/* 大小限制，不能超过20M */
		if (file.getSize() > 1024 * 1024 * 20) {
			resultMap.put("message", "上传错误：上传错误：大小超过20M");
			return resultMap;
		}
		// 检测后缀
		String extension = ImageUtil.getSuffix(file.getOriginalFilename());
		if (extension == null) {
			resultMap.put("message", "上传错误：该文件不是图片");
			return resultMap;
		}
		extension = extension.toLowerCase();
		if (!Config.IMAGE_SUFFIX.contains(extension)) {
			resultMap.put("message", "上传错误：文件后缀只支持：" + Config.IMAGE_SUFFIX);
			return resultMap;
		}
		// 建立目录（存放的图片）
		File localFile = new File(folder);
		if (!localFile.exists()) {
			boolean res = localFile.mkdirs();
			if (!res) {
				logger.error("上传错误：上传目录创建失败");
				resultMap.put("message", "上传错误：上传目录创建失败");
				return resultMap;
			}
		}
		// 获取新的文件名，无后缀
		String fileName = ImageUtil.getNameByTime();
		String lastFileName;
		// 如果是动图，直接上传，否则压缩成jpg后上传
		if (extension.equalsIgnoreCase(GIF)) {
			lastFileName = folder + fileName + GIF;
			try {
				uploadLocalImage(file.getInputStream(), new File(fileName));
			} catch (IOException e) {
				logger.error("上传错误：图片压缩成jpg失败");
				resultMap.put("message", "上传错误：图片压缩成jpg失败");
				return resultMap;
			}
		} else {
			lastFileName = fileName + JPG;
			fileName += JPG;
			localFile = new File(folder + fileName);
			try {
				ImageUtil.zipImage(file.getInputStream(), localFile, Config.IMAGE_MAX_SIZE, Config.IMAGE_ZIP_QUALITY);
			} catch (Exception e) {
				logger.error("上传错误：图片压缩成jpg失败");
				resultMap.put("message", "上传错误：图片压缩成jpg失败");
				return resultMap;
			}
		}
		/* 对数据库进行插入操作 */
		Image image = new Image();
		image.setName(lastFileName);
		if (bid < 0) {
			image.setName(Config.LOCAL_PHOTOS_FOLDER + lastFileName);
		} else {
			image.setName(Config.LOCAL_BLOG_FOLDER + lastFileName);
		}
		image.setBid(bid);
		image.setPubtime(new Timestamp(System.currentTimeMillis()));
		imageDao.insertImage(image);
		resultMap.put("success", 1);
		resultMap.put("message", "上传成功");
		if (bid < 0) {
			resultMap.put("url", ConfigI.LOCAL_URL_PREFIX + Config.LOCAL_PHOTOS_FOLDER + lastFileName);
		} else {
			resultMap.put("url", ConfigI.LOCAL_URL_PREFIX + Config.LOCAL_BLOG_FOLDER + lastFileName);
		}
		return resultMap;
	}

	@Override
	public String deleteImage(Integer iid, Object adminToken) {
		// 用户鉴权
		if (!Config.TOKEN_DO_LOGIN.equals(adminToken)) {
			return "删除失败：您并未拥有删除权限。";
		}
		/* 查询处理 */
		Image image = imageDao.queryImage(iid);
		if (image == null || image.getName() == null) {
			return "删除失败：数据库中不存在，请联系系统管理员。";
		}
		String result;
		int deleteImageResult = imageDao.deleteImage(iid);
		String imageName = image.getName();
		if (deleteImageResult > 0) {
			try {
				File file = new File(imageName);
				if (file.delete()) {
					result = "文件已删除, ";
				} else {
					result = "数据库已删除，服务器删除失败！";
				}
			} catch (Exception e) {
				result = "删除文件失败";
				logger.error("删除文件失败： " + e.getMessage());
			}

		} else {
			try {
				File file = new File(imageName);
				if (file.delete()) {
					result = "服务器尝试删除成功, ";
				} else {
					result = "删除失败！";
				}
			} catch (Exception e) {
				result = "删除失败！";
				logger.error("删除文件失败： " + e.getMessage());
			}
		}
		return result + "数据库删除数量：" + deleteImageResult;
	}

	@Override
	public List<Image> listImage(int page, int number) {
		return imageDao.listImageByPage((page - 1) * number, number);
	}

	@Override
	public int countImage() {
		return imageDao.getTotal();
	}

	/**
	 * 文件保存工具
	 */
	private void uploadLocalImage(InputStream inputStream, File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte[1024];
			while ((inputStream.read(b)) != -1) {
				// 写入数据
				fos.write(b);
			}
			inputStream.close();
			// 保存数据
			fos.close();
		} catch (IOException e) {
			logger.info("文件上传失败！");
		}
	}
}
