package com.example.demo.model.nhanvien;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.Nguoi;
@Entity
@Table(name = "nhanvien")
public class NhanVien extends Nguoi {
	@ManyToOne
	@JoinColumn(name = "mabp")
	private BoPhan boPhan;
	@Column(name = "isketoan")
	private int isKeToan;
	@Column(name = "isthungan")
	private int isThuNgan;
	@Column(name = "tentk")
	private String tenTK;
	@Column(name = "matkhau")
	private String matKhau;

	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVien(String id, String ten, String diaChi, String dienThoai, String maSoThue, BoPhan boPhan, int isKeToan, int isThuNgan, String tenTK, String matKhau) {
		super(id,ten,diaChi,dienThoai,maSoThue);
		this.boPhan = boPhan;
		this.isKeToan = isKeToan;
		this.isThuNgan = isThuNgan;
		this.tenTK = tenTK;
		this.matKhau = matKhau;
	}

	public BoPhan getBoPhan() {
		return boPhan;
	}

	public void setBoPhan(BoPhan boPhan) {
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

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

}