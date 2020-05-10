package com.yiyjm.nest.entity;

/**
 * name 名称
 * value 数量
 *
 * @author jonny
 * @date 2020/04/30
 */
public class Chart {
	private String name;
	private Integer num;

	/**
	 * getName
	 *
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName
	 *
	 * @param name 名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得全国矿工工会
	 *
	 * @return {@link Integer}
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 全国矿工工会组
	 *
	 * @param num 全国矿工工会
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * 字符串
	 *
	 * @return {@link String}
	 */
	@Override
	public String toString() {
		return "Chart{" +
				"name='" + name + '\'' +
				", num=" + num +
				'}';
	}
}
