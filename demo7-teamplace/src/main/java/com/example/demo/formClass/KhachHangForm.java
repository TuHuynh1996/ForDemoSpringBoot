package com.example.demo.formClass;

public class KhachHangForm {
	private String id;
	private String ten;
	private String diaChi;
	private String dienThoai;
	private String maSoThue;
	private String nhomKhach;
	private double thuDK;
	private double traDK;

	public KhachHangForm() {
	}

	public KhachHangForm(String id, String ten, String diaChi, String dienThoai, String maSoThue, String nhomKhach,
			double thuDK, double traDK) {
		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.maSoThue = maSoThue;
		this.nhomKhach = nhomKhach;
		this.thuDK = thuDK;
		this.traDK = traDK;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public String getNhomKhach() {
		return nhomKhach;
	}

	public void setNhomKhach(String nhomKhach) {
		this.nhomKhach = nhomKhach;
	}

	public double getThuDK() {
		return thuDK;
	}

	public void setThuDK(double thuDK) {
		this.thuDK = thuDK;
	}

	public double getTraDK() {
		return traDK;
	}

	public void setTraDK(double traDK) {
		this.traDK = traDK;
	}

}
