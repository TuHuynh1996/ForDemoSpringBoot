package com.example.demo.model.chungtu;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.model.khachhang.KhachHang;

@Entity
@DiscriminatorValue("5")
public class DonDatHang extends ChungTu {
	@ManyToOne
	private KhachHang khachHang;
	@Temporal(TemporalType.DATE)
	private Date ngayDat;
	private String gioDat;
	private int soKhach;
	private long soTien;
	private double giam;
	private long traTruoc;

	public DonDatHang() {
		super();
	}	

	public DonDatHang(String id, Date ngayCT, String noiDung, int trangThai, KhachHang khachHang, Date ngayDat,
			String gioDat, int soKhach, long soTien,double d, long traTruoc) {
		super(id, ngayCT, noiDung, trangThai);
		this.khachHang = khachHang;
		this.ngayDat = ngayDat;
		this.gioDat = gioDat;
		this.soKhach = soKhach;
		this.soTien = soTien;
		this.giam = d;
		this.traTruoc = traTruoc;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public String getGioDat() {
		return gioDat;
	}

	public void setGioDat(String gioDat) {
		this.gioDat = gioDat;
	}

	public int getSoKhach() {
		return soKhach;
	}

	public void setSoKhach(int soKhach) {
		this.soKhach = soKhach;
	}

	public long getSoTien() {
		return soTien;
	}

	public void setSoTien(long soTien) {
		this.soTien = soTien;
	}

	public double getGiam() {
		return giam;
	}

	public void setGiam(double giam) {
		this.giam = giam;
	}

	public long getTraTruoc() {
		return traTruoc;
	}

	public void setTraTruoc(long traTruoc) {
		this.traTruoc = traTruoc;
	}
}
