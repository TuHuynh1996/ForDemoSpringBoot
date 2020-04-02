package com.example.demo.model.khachhang;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhomkhachhang")
public class NhomKhach {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	@Column(name = "Macha")
	private String maCha;
	@Column(name = "Tennhom")
	private String ten;
	@Column(name = "Loainhom")
	private int loaiNhom;
	@javax.persistence.Transient
	private List<NhomKhach> con;

	public NhomKhach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhomKhach(String id, String maCha, String ten, int loaiNhom) {
		super();
		this.id = id;
		this.maCha = maCha;
		this.ten = ten;
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

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getLoaiNhom() {
		return loaiNhom;
	}

	public void setLoaiNhom(int loaiNhom) {
		this.loaiNhom = loaiNhom;
	}
	public List<NhomKhach> getCon() {
		return con;
	}

	public void setCon(List<NhomKhach> con) {
		List<NhomKhach> children = new ArrayList<NhomKhach>();
		for(int i = 0; i < con.size(); i++) {
			if(con.get(i).getMaCha().equals(this.id)) {
				children.add(con.get(i));
			}
		}
		this.con = children;
	}

}