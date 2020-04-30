package com.yiyjm.nest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.yiyjm.nest.config.Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Jonny.Chang
 * @date 2020/05/01
 */
public class RobotUtil {

	/**
	 * get lilirobotmessage
	 *
	 * @param info
	 * @return {@link String}
	 */
	public static String getLiliRobotMessage(String info) {
		Scanner scanner = null;
		InputStream inputStream = null;
		HttpURLConnection urlConnection = null;
		JsonNode tree = null;
		String text = "";

		try {
			URL url = new URL("http://i.itpk.cn/api.php?api_key=" + Config.LILI_API_KEY + "&api_secret=" + Config.LILI_API_SECRET + "&question=" + info);
			urlConnection = (HttpURLConnection) url.openConnection();

			inputStream = urlConnection.getInputStream();
			scanner = new Scanner(inputStream, "UTF-8");
			StringBuilder builder = new StringBuilder();

			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine());
			}

			text = builder.toString();
		} catch (Exception e) {
			return null;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {

				}
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return text;
	}

	/**
	 * get robotmessage
	 *
	 * @param info
	 * @return {@link String}
	 */
	public static String getRobotMessage(String info) {
		Scanner scanner = null;
		InputStream inputStream = null;
		HttpURLConnection urlConnection = null;
		JsonNode tree = null;
		String text = "";

		try {
			URL url = new URL("http://www.tuling123.com/openapi/api?key=" + Config.TULING_KEY + "&info=" + info);
			urlConnection = (HttpURLConnection) url.openConnection();

			inputStream = urlConnection.getInputStream();
			scanner = new Scanner(inputStream, "UTF-8");
			StringBuilder builder = new StringBuilder();

			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine());
			}

			text = builder.toString();
			tree = Jackson.getObjectMapper().readTree(text);
			if (tree.path("text") != null) {
				text = tree.path("text").asText();
			} else {
				text = null;
			}
		} catch (Exception e) {
			return null;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {

				}
			}
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return text;
	}

}
