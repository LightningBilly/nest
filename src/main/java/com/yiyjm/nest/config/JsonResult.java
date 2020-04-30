package com.yiyjm.nest.config;

/**
 * json 返回的结构
 *
 * @author jonny
 * @date 2020/04/30
 */
public class JsonResult<T> {

	/**
	 * code=0表示成功，返回数据data。code!=0，表示错误，返回错误信息msg
	 */
	private int code;
	private T data;
	private String msg;

	/**
	 * json结果
	 */
	public JsonResult() {
		code = 0;
	}

	/**
	 * 好吧
	 *
	 * @param data 数据
	 * @param msg  味精
	 * @return {@link JsonResult<T>}
	 */
	public JsonResult<T> ok(T data, String msg) {
		this.code = 0;
		this.data = data;
		this.msg = msg;
		return this;
	}

	/**
	 * 错误
	 *
	 * @param code 代码
	 * @param msg  味精
	 * @return {@link JsonResult<T>}
	 */
	public JsonResult<T> error(int code, String msg) {
		this.code = code;
		this.msg = msg;
		return this;
	}

	/**
	 * 获取数据
	 *
	 * @return {@link T}
	 */
	public T getData() {
		return data;
	}

	/**
	 * 集数据
	 *
	 * @param data 数据
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 获取代码
	 *
	 * @return int
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置代码
	 *
	 * @param code 代码
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 得到味精
	 *
	 * @return {@link String}
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置味精
	 *
	 * @param msg 味精
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
