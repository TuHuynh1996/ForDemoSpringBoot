package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Nguoi {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private String id;
	@Column(name = "Hoten")
	private String ten;
	@Column(name = "Diachi")
	private String diaChi;
	@Column(name = "Dienthoai")
	private String dienThoai;
	@Column(name = "Masothue")
	private String maSoThue;
//	@PrePersist
//	private void ensureId(){
//	    this.setId(UUID.randomUUID().toString());
//	}

	public Nguoi() {
		super();
	}

	public Nguoi(String id, String ten, String diaChi, String dienThoai, String maSoThue) {
//		super();
		this.id = id;
		this.ten = ten;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
		this.maSoThue = maSoThue;
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

}