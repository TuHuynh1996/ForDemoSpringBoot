package com.example.demo.model.sanpham;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhomhang")
public class NhomHang {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="macha")
	private String maCha;
	@Column(name="tennhom")
	private String ten;
	@Column(name="loainhom")
	private int loaiNhom;
	@javax.persistence.Transient
	private List<NhomHang> con;

	public NhomHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhomHang(String id, String maCha, String tenNhom, int loaiNhom) {
		super();
		this.id = id;
		this.maCha = maCha;
		this.ten = tenNhom;
		this.loaiNhom = loaiNhom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaCha() {
		return maCha;
	}

	public void setMaCha(String maCha) {
		this.maCha = maCha;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String tenNhom) {
		this.ten = tenNhom;
	}

	public int getLoaiNhom() {
		return loaiNhom;
	}

	public void setLoaiNhom(int loaiNhom) {
		this.loaiNhom = loaiNhom;
	}
	public List<NhomHang> getCon() {
		return con;
	}

	public void setCon(List<NhomHang> con) {
		List<NhomHang> children = new ArrayList<NhomHang>();
		for(int i = 0; i < con.size(); i++) {
			if(con.get(i).getMaCha().equals(this.id)) {
				children.add(con.get(i));
			}
		}
		this.con = children;
	}

}