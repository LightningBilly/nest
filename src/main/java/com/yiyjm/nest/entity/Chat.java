package com.yiyjm.nest.entity;

import java.sql.Timestamp;

/**
 * 聊天
 *
 * @author jonny
 * @date 2020/04/30
 */
public class Chat {
	private Integer cid;
	/**
	 * 性别，0宝宝，1我，2游客
	 */
	private Byte gender;
	private String content;
	private Timestamp pubtime;

	/**
	 * 得到cid
	 *
	 * @return {@link Integer}
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * 设置cid
	 *
	 * @param cid cid
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * 让男
	 *
	 * @return {@link Byte}
	 */
	public Byte getGender() {
		return gender;
	}

	/**
	 * 组男性
	 *
	 * @param gender 男性
	 */
	public void setGender(Byte gender) {
		this.gender = gender;
	}

	/**
	 * 获取内容
	 *
	 * @return {@link String}
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 *
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 得到pubtime
	 *
	 * @return {@link Timestamp}
	 */
	public Timestamp getPubtime() {
		return pubtime;
	}

	/**
	 * 设置pubtime
	 *
	 * @param pubtime pubtime
	 */
	public void setPubtime(Timestamp pubtime) {
		this.pubtime = pubtime;
	}

	/**
	 * 字符串
	 *
	 * @return {@link String}
	 */
	@Override
	public String toString() {
		return "Chat{" +
				"cid=" + cid +
				'}';
	}
}
