package com.yitianyike.myssm.entity;

/**
 * @author fuqiang
 *统一的返回对象
 */
public class ResponseResult{
	
	private int code = 0;
	private String msg = "Success";	
	private Object respResult = "";
	
	public ResponseResult() {
		super();
	}

	
	public ResponseResult(Object respResult) {
		super();
		this.respResult = respResult;
	}


	public ResponseResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ResponseResult(int code, String msg, Object respResult) {
		super();
		this.code = code;
		this.msg = msg;
		this.respResult = respResult;
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

	public Object getRespResult() {
		return respResult;
	}

	public void setRespResult(Object respResult) {
		this.respResult = respResult;
	}
}
