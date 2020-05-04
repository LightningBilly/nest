package com.yiyjm.nest.util;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Local image util
 *
 * @author jonny
 */
public class LocalImageUtil {
	public static final String JPEG = "jpeg";

	/**
	 * 压缩图片
	 *
	 * @param inputStream 原文件
	 * @param file        压缩后保存位置
	 * @param maxSize     最大边长
	 * @param quality     压缩质量
	 */
	public static void zipImage(InputStream inputStream, File file, float maxSize, float quality)
			throws IOException {
		BufferedImage srcFile = ImageIO.read(inputStream);
		int width = srcFile.getWidth();
		int height = srcFile.getHeight();

		// 按比例缩放图标，不能超过 maxSize
		if (width > maxSize || height > maxSize) {
			if (width > height) {
				float bit = maxSize / width;
				width = (int) (width * bit);
				height = (int) (height * bit);
			} else {
				float bit = maxSize / height;
				width = (int) (width * bit);
				height = (int) (height * bit);
			}
		}

		/* 宽,高设定 */
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

		/* 压缩质量 */
		ImageWriter imageWriter = ImageIO.getImageWritersByFormatName(JPEG).next();
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
		imageWriteParam.setCompressionQuality(quality);
		ColorModel colorModel = srcFile.getColorModel();
		imageWriteParam.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		/* 存放位置 */
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		imageWriter.reset();
		imageWriter.setOutput(ImageIO.createImageOutputStream(fileOutputStream));
		imageWriter.write(null, new IIOImage(image, null, null), imageWriteParam);

		fileOutputStream.flush();
		fileOutputStream.close();
	}

	/**
	 * get suffix
	 *
	 * @param name 获取文件后缀名
	 * @return {@link String}
	 */
	public static String getSuffix(String name) {
		if (name == null) {
			return null;
		}
		int lastDot = name.lastIndexOf(CommonConstants.DOT);
		/* 有后缀名，例如 .txt .jpg */
		if (lastDot != -1) {
			return name.substring(lastDot);
		}
		return null;
	}

}
