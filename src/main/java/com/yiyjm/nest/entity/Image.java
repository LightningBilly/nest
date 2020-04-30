package com.yiyjm.nest.entity;

import java.sql.Timestamp;

/**
 * Image
 *
 * @author jonny
 * @date 2020/04/30
 */
public class Image {
	private Integer iid;
	/**
	 * <0 bid，-1小相册
	 */
	private Integer bid;
	private String name;
	private Timestamp pubtime;

	/**
	 * 得到iid
	 *
	 * @return {@link Integer}
	 */
	public Integer getIid() {
		return iid;
	}

	/**
	 * iid设置
	 *
	 * @param iid iid
	 */
	public void setIid(Integer iid) {
		this.iid = iid;
	}

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
	 * 得到的名字
	 *
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * 集名称
	 *
	 * @param name 的名字
	 */
	public void setName(String name) {
		this.name = name;
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
}
