package com.example.demo.model.khachhang;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.Nguoi;
@Entity
@Table(name = "khachhang")
public class KhachHang extends Nguoi {
	@ManyToOne
	@JoinColumn(name = "Manhom")
	private NhomKhach nhomKhach;
	@Column(name = "ThuDK")
	private double thuDK;
	@Column(name = "TraDK")
	private double traDK;

	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHang(String id, String ten, String diaChi, String dienThoai,String maSoThue,NhomKhach nhomKhach, double thuDK, double traDK) {
		super(id,ten,diaChi,dienThoai,maSoThue);
		this.nhomKhach = nhomKhach;
		this.thuDK = thuDK;
		this.traDK = traDK;
	}

	public NhomKhach getNhomKhach() {
		return nhomKhach;
	}

	public void setNhomKhach(NhomKhach nhomKhach) {
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