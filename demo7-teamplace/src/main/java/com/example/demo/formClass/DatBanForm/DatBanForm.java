package com.example.demo.formClass.DatBanForm;

import java.util.List;

public class DatBanForm {
	private String id;
	private String ngayLap;
	private String khachHang;
	private String ngayDat;
	private String gioDat;
	private int soKhach;
	private String bangGia;
	private double giam;
	private long tienDatTruoc;
	private String ghiChu;
	private List<ThucDonForm> thucDon;

	public DatBanForm(String id, String ngayLap, String khachHang, String ngayDat, String gioDat, int soKhach,
			String bangGia, double giam, long tienDatTruoc, String ghiChu, List<ThucDonForm> thucDon) {
		super();
		this.id = id;
		this.ngayLap = ngayLap;
		this.khachHang = khachHang;
		this.ngayDat = ngayDat;
		this.gioDat = gioDat;
		this.soKhach = soKhach;
		this.bangGia = bangGia;
		this.giam = giam;
		this.tienDatTruoc = tienDatTruoc;
		this.ghiChu = ghiChu;
		this.thucDon = thucDon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}

	public String getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(String ngayDat) {
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

	public String getBangGia() {
		return bangGia;
	}

	public void setBangGia(String bangGia) {
		this.bangGia = bangGia;
	}

	public double getGiam() {
		return giam;
	}

	public void setGiam(double giam) {
		this.giam = giam;
	}

	public long getTienDatTruoc() {
		return tienDatTruoc;
	}

	public void setTienDatTruoc(long tienDatTruoc) {
		this.tienDatTruoc = tienDatTruoc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public List<ThucDonForm> getThucDon() {
		return thucDon;
	}

	public void setThucDon(List<ThucDonForm> thucDon) {
		this.thucDon = thucDon;
	}

}
