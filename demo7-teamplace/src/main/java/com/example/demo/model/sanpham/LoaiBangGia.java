package com.example.demo.model.sanpham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "loaibanggia")
public class LoaiBangGia implements Comparable<LoaiBangGia> {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "tenbg")
	private String tenBG;
	@Column(name = "macdinh")
	private int macDinh;

	public LoaiBangGia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoaiBangGia(String id, String tenBG, int macDinh) {
		super();
		this.id = id;
		this.tenBG = tenBG;
		this.macDinh = macDinh;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenBG() {
		return this.tenBG;
	}

	public void setTenBG(String tenBG) {
		this.tenBG = tenBG;
	}

	public int getMacDinh() {
		return this.macDinh;
	}

	public void setMacDinh(int macDinh) {
		this.macDinh = macDinh;
	}

	public int compareTo(LoaiBangGia o) {
		return this.id.compareTo(o.getId());
	}

}