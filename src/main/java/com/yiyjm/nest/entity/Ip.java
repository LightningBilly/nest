package com.yiyjm.nest.entity;

import java.sql.Timestamp;

/**
 * Ip
 *
 * @author jonny
 * @date 2020/04/30
 */
public class Ip {
	private Integer iid;
	private String ip;
	private String region;
	private String city;
	private String isp;
	private Timestamp curtime;

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
	 * 获得知识产权
	 *
	 * @return {@link String}
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 设置ip
	 *
	 * @param ip 知识产权
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 得到区域
	 *
	 * @return {@link String}
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * 设置区域
	 *
	 * @param region 地区
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 得到城市
	 *
	 * @return {@link String}
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置城市
	 *
	 * @param city 城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 得到isp
	 *
	 * @return {@link String}
	 */
	public String getIsp() {
		return isp;
	}

	/**
	 * 设置isp
	 *
	 * @param isp isp
	 */
	public void setIsp(String isp) {
		this.isp = isp;
	}

	/**
	 * 得到curtime
	 *
	 * @return {@link Timestamp}
	 */
	public Timestamp getCurtime() {
		return curtime;
	}

	/**
	 * 设置curtime
	 *
	 * @param curtime curtime
	 */
	public void setCurtime(Timestamp curtime) {
		this.curtime = curtime;
	}

	/**
	 * 字符串
	 *
	 * @return {@link String}
	 */
	@Override
	public String toString() {
		return "Ip{" +
				"iid=" + iid +
				", ip='" + ip + '\'' +
				", region='" + region + '\'' +
				", city='" + city + '\'' +
				", isp='" + isp + '\'' +
				", curtime=" + curtime +
				'}';
	}
}
