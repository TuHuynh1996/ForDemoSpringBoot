package com.example.demo.model.sanpham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "donvitinh")
public class DonViTinh {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="ten")
	private String ten;
	@Column(name="macdinh")
	private int macDinh;

	public DonViTinh() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DonViTinh(String id, String ten, int macDinh) {
		super();
		this.id = id;
		this.ten = ten;
		this.macDinh = macDinh;
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

	public int getMacDinh() {
		return macDinh;
	}

	public void setMacDinh(int macDinh) {
		this.macDinh = macDinh;
	}

}