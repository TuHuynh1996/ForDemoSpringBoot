package com.example.demo.formClass;

import java.util.List;

public class ThanhPhanForm {
	private String sanphamId;
	private String dvtId;
	private List<String> listTen;
	private List<String> listDVT;
	private List<Double> listSoLuong;

	public ThanhPhanForm(String sanphamId, String dvtId, List<String> listTen, List<String> listDVT, List<Double> listSoLuong) {
		super();
		this.sanphamId = sanphamId;
		this.dvtId = dvtId;
		this.listTen = listTen;
		this.listDVT = listDVT;
		this.listSoLuong = listSoLuong;
	}
	
	public String getSanphamId() {
		return sanphamId;
	}

	public void setSanphamId(String sanphamId) {
		this.sanphamId = sanphamId;
	}

	public String getDvtId() {
		return dvtId;
	}

	public void setDvtId(String dvtId) {
		this.dvtId = dvtId;
	}

	public List<String> getListTen() {
		return listTen;
	}

	public void setListTen(List<String> listTen) {
		this.listTen = listTen;
	}

	public List<String> getListDVT() {
		return listDVT;
	}

	public void setListDVT(List<String> listDVT) {
		this.listDVT = listDVT;
	}

	public List<Double> getListSoLuong() {
		return listSoLuong;
	}

	public void setListSoLuong(List<Double> listSoLuong) {
		this.listSoLuong = listSoLuong;
	}

}
