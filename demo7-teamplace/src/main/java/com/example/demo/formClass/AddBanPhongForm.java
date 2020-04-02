package com.example.demo.formClass;

import java.util.List;

public class AddBanPhongForm {
	private List<String> id;
	private List<String> khuVuc;
	private List<String> bangGia;
	private List<Integer> status;

	public AddBanPhongForm(List<String> id, List<String> khuVuc, List<String> bangGia, List<Integer> status) {
		super();
		this.id = id;
		this.khuVuc = khuVuc;
		this.bangGia = bangGia;
		this.status = status;
	}

	public List<String> getId() {
		return id;
	}

	public void setId(List<String> id) {
		this.id = id;
	}

	public List<String> getKhuVuc() {
		return khuVuc;
	}

	public void setKhuVuc(List<String> khuVuc) {
		this.khuVuc = khuVuc;
	}

	public List<String> getBangGia() {
		return bangGia;
	}

	public void setBangGia(List<String> bangGia) {
		this.bangGia = bangGia;
	}

	public List<Integer> getStatus() {
		return status;
	}

	public void setStatus(List<Integer> status) {
		this.status = status;
	}

}
