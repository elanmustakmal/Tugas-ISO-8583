package com.iso.client.configuration;

//created by Dwi s - Agustus 2020

public class SwitcherCustomException extends RuntimeException {

	private static final long serialVersionUID = 7403433302241782474L;
	private String errorNum;

	public SwitcherCustomException(String errorNum, String errorMessage) {
		super(errorMessage);
		this.errorNum = errorNum;
	}

	public SwitcherCustomException(String errorNum, String errorMessage, Throwable err) {
		super(errorMessage, err);
		this.errorNum = errorNum;
	}


	public String getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(String errorNum) {
		this.errorNum = errorNum;
	}

}
