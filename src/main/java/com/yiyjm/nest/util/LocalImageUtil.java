package com.yiyjm.nest.util;

import javax.imageio.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

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

	/**
	 * 生成验证码
	 *
	 * @param request  request 请求
	 * @param response response 响应
	 * @param veriname veriname 验证名称
	 * @throws IOException IO异常
	 */
	public static void getVeri(HttpServletRequest request, HttpServletResponse response, String veriname) throws IOException {
		int width = 120;
		int height = 30;

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		// backgroud
		g.setColor(new Color(249, 249, 249));
		g.fillRect(0, 0, width, height);

		// setBorder(g);
		g.setColor(new Color(210, 209, 205));
		g.drawRect(1, 1, width - 2, height - 2);

		//drawRandomLine(g);
		g.setColor(Color.GRAY);
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(width);
			int y1 = new Random().nextInt(height);
			int x2 = new Random().nextInt(width);
			int y2 = new Random().nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}

		String random = drawRandomNum((Graphics2D) g);
		request.getSession().setAttribute(veriname, random);
		response.setContentType("image/jpeg");
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}

	/**
	 * 解析随机数字
	 *
	 * @param g 供对几何形状、坐标转换、颜色管理和文本布局更为复杂的控制对象
	 * @return string
	 */
	private static String drawRandomNum(Graphics2D g) {
		StringBuilder sb = new StringBuilder();
		g.setFont(new Font("cmr10", Font.BOLD, 20));
		String base = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
		int x = 5;
		int m = 4;
		for (int i = 0; i < m; i++) {
			int R = new Random().nextInt(256);
			int G = new Random().nextInt(256);
			int B = new Random().nextInt(256);
			g.setColor(new Color(R, G, B));
			int degree = new Random().nextInt() % 30;
			String ch = base.charAt(new Random().nextInt(base.length())) + "";
			sb.append(ch);
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(ch, x, 20);
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		return sb.toString();
	}

}
