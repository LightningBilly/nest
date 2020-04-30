package com.yiyjm.nest.entity;

import java.sql.Timestamp;

/**
 * 博客
 *
 * @author jonny
 * @date 2020/04/30
 */
public class Blog {
	private Integer bid;
	private Byte rank;
	private String title;
	private String keyword;
	private String content;
	private Timestamp modtime;
	private String url;

	/**
	 * 得到报价
	 *
	 * @return {@link Integer}
	 */
	public Integer getBid() {
		return bid;
	}

	/**
	 * 设置出价
	 *
	 * @param bid 报价
	 */
	public void setBid(Integer bid) {
		this.bid = bid;
	}

	/**
	 * 获得排名
	 *
	 * @return {@link Byte}
	 */
	public Byte getRank() {
		return rank;
	}

	/**
	 * 设置等级
	 *
	 * @param rank 排名
	 */
	public void setRank(Byte rank) {
		this.rank = rank;
	}

	/**
	 * 获得冠军
	 *
	 * @return {@link String}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 *
	 * @param title 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 得到关键字
	 *
	 * @return {@link String}
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置关键字
	 *
	 * @param keyword 关键字
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	 * 得到modtime
	 *
	 * @return {@link Timestamp}
	 */
	public Timestamp getModtime() {
		return modtime;
	}

	/**
	 * 设置modtime
	 *
	 * @param modtime modtime
	 */
	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}

	/**
	 * 获取url
	 *
	 * @return {@link String}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置网址
	 *
	 * @param url url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}

