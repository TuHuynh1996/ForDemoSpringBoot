package com.example.demo.formClass.DatBanForm;

public class ThucDonForm {
	private String id;
	private double soLuong;
	private String ghiChu;
	
	public ThucDonForm(String id, double soLuong, String ghiChu) {
		super();
		this.id = id;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

}
