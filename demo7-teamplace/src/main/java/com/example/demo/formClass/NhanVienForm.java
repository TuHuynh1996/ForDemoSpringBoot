package com.example.demo.formClass;

public class NhanVienForm {
	private String id;
	private String ten;
	private String diaChi;
	private String dienThoai;
	private String maSoThue;
	private String boPhan;
	private int isKeToan;
	private int isThuNgan;
	private String tenTk;
	private	String matKhau;
	
	public NhanVienForm(String id, String ten, String diaChi, String dienThoai, String maSoThue, String boPhan,
			int isKeToan, int isThuNgan, String tenTk, String matKhau) {
		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.maSoThue = maSoThue;
		this.boPhan = boPhan;
		this.isKeToan = isKeToan;
		this.isThuNgan = isThuNgan;
		this.tenTk = tenTk;
		this.matKhau = matKhau;
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
	public String getBoPhan() {
		return boPhan;
	}
	public void setBoPhan(String boPhan) {
		this.boPhan = boPhan;
	}
	public int getIsKeToan() {
		return isKeToan;
	}
	public void setIsKeToan(int isKeToan) {
		this.isKeToan = isKeToan;
	}
	public int getIsThuNgan() {
		return isThuNgan;
	}
	public void setIsThuNgan(int isThuNgan) {
		this.isThuNgan = isThuNgan;
	}
	public String getTenTk() {
		return tenTk;
	}
	public void setTenTk(String tenTk) {
		this.tenTk = tenTk;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
}
