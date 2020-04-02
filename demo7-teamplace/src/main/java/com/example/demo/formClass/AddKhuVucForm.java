package com.example.demo.formClass;

import java.util.List;

public class AddKhuVucForm {
	private List<String> id;
	private List<String> tenKhuVuc;

	public AddKhuVucForm(List<String> id, List<String> tenKhuVuc) {
		super();
		this.id = id;
		this.tenKhuVuc = tenKhuVuc;
	}

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getTenKhuVuc() {
		return tenKhuVuc;
	}

	public void setTenKhuVuc(List<String> tenKhuVuc) {
		this.tenKhuVuc = tenKhuVuc;
	}
	
}
