package com.example.demo.formClass;

import org.springframework.http.HttpStatus;

public class Sms {
	private String id;
	private String status;

	public Sms() {
	}

	public Sms(String id, String status) {
		super();
		this.id = id;
		this.status = status;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpStatus getStatusHttp() {
		HttpStatus statusHttp;
		if (this.id.equals("-1")) {
			statusHttp = HttpStatus.BAD_REQUEST;
			System.out.println(statusHttp);
		} else {
			statusHttp = HttpStatus.OK;
		}
		return statusHttp;
	}
}
