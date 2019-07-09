package com.gaia.util;

/**
 * 返回结果类
 */
public class AjaxResult {

	private int code;
	private String msg;
	private Object data;

	
	public AjaxResult() {
		super();
	}

	
	public AjaxResult(int code, String msg, Object data) {
		setCode(code);
		setMsg(msg);
		setData(data);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
	public static AjaxResult success(Integer code, String msg, Object data) {
		return new AjaxResult(code, msg , data);
	}
	
	public static AjaxResult success(String msg, Object data) {
		return new AjaxResult(200, msg , data);
	}
	
	public static AjaxResult fail(Integer code,String msg) {
		return new AjaxResult(code, msg , null);
	}
	
	public static AjaxResult fail(Integer code,String msg, Object data) {
		return new AjaxResult(code, msg , data);
	}
	
	@Override
	public String toString() {
		return "AjaxResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}

	
	
	
}
