package com.yiyjm.nest.entity;

import java.sql.Timestamp;

/**
 * Letter
 *
 * @author jonny
 * @date 2020/04/30
 */
public class Letter {
	private Integer lid;
	private Integer zan;
	private String nickname;
	private String content;
	private String ip;
	private Timestamp pubtime;

	/**
	 * get 盖子
	 *
	 * @return {@link Integer}
	 */
	public Integer getLid() {
		return lid;
	}

	/**
	 * set 盖子
	 *
	 * @param lid 盖子
	 */
	public void setLid(Integer lid) {
		this.lid = lid;
	}

	/**
	 * get 攒
	 *
	 * @return {@link Integer}
	 */
	public Integer getZan() {
		return zan;
	}

	/**
	 * set 攒
	 *
	 * @param zan 攒
	 */
	public void setZan(Integer zan) {
		this.zan = zan;
	}

	/**
	 * get 昵称
	 *
	 * @return {@link String}
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * set 昵称
	 *
	 * @param nickname 昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * get 内容
	 *
	 * @return {@link String}
	 */
	public String getContent() {
		return content;
	}

	/**
	 * set 内容
	 *
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * get 知识产权
	 *
	 * @return {@link String}
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * set 知识产权
	 *
	 * @param ip 知识产权
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * get pubtime
	 *
	 * @return {@link Timestamp}
	 */
	public Timestamp getPubtime() {
		return pubtime;
	}

	/**
	 * set pubtime
	 *
	 * @param pubtime pubtime
	 */
	public void setPubtime(Timestamp pubtime) {
		this.pubtime = pubtime;
	}

}
