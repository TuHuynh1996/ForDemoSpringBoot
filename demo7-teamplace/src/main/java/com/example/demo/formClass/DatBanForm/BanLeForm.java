package com.example.demo.formClass.DatBanForm;

import java.util.List;

public class BanLeForm {
	private String soBan;
	private String soPhieu;
	private String khachHang;
	private int soLuongKhach;
	private String gioVao;
	private String phucVu;
	private String thuNgan;
	private long traTruoc;
	private long conNo;
	private long giam;
	private long thue;
	private long phiPV;
	private String ghiChu;
	private String thanhToan;
	private List<ThucDonForm> thucDon;

	public BanLeForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BanLeForm(String soBan, String soPhieu, String khachHang, int soLuongKhach, String gioVao, String phucVu,
			String thuNgan, long traTruoc,long conNo, long giam, long thue, long phiPV, String ghiChu, String thanhToan,
			List<ThucDonForm> thucDon) {
		super();
		this.soBan = soBan;
		this.soPhieu = soPhieu;
		this.khachHang = khachHang;
		this.soLuongKhach = soLuongKhach;
		this.gioVao = gioVao;
		this.phucVu = phucVu;
		this.thuNgan = thuNgan;
		this.traTruoc = traTruoc;
		this.conNo = conNo;
		this.giam = giam;
		this.thue = thue;
		this.phiPV = phiPV;
		this.ghiChu = ghiChu;
		this.thanhToan = thanhToan;
		this.thucDon = thucDon;
	}

	public String getSoBan() {
		return soBan;
	}

	public void setSoBan(String soBan) {
		this.soBan = soBan;
	}

	public String getSoPhieu() {
		return soPhieu;
	}

	public void setSoPhieu(String soPhieu) {
		this.soPhieu = soPhieu;
	}

	public String getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}

	public int getSoLuongKhach() {
		return soLuongKhach;
	}

	public void setSoLuongKhach(int soLuongKhach) {
		this.soLuongKhach = soLuongKhach;
	}

	public String getGioVao() {
		return gioVao;
	}

	public void setGioVao(String gioVao) {
		this.gioVao = gioVao;
	}

	public String getPhucVu() {
		return phucVu;
	}

	public void setPhucVu(String phucVu) {
		this.phucVu = phucVu;
	}

	public String getThuNgan() {
		return thuNgan;
	}

	public void setThuNgan(String thuNgan) {
		this.thuNgan = thuNgan;
	}

	public long getTraTruoc() {
		return traTruoc;
	}

	public void setTraTruoc(long traTruoc) {
		this.traTruoc = traTruoc;
	}

	public long getConNo() {
		return conNo;
	}

	public void setConNo(long conNo) {
		this.conNo = conNo;
	}

	public long getGiam() {
		return giam;
	}

	public void setGiam(long giam) {
		this.giam = giam;
	}

	public long getThue() {
		return thue;
	}

	public void setThue(long thue) {
		this.thue = thue;
	}

	public long getPhiPV() {
		return phiPV;
	}

	public void setPhiPV(long phiPV) {
		this.phiPV = phiPV;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(String thanhToan) {
		this.thanhToan = thanhToan;
	}

	public List<ThucDonForm> getThucDon() {
		return thucDon;
	}

	public void setThucDon(List<ThucDonForm> thucDon) {
		this.thucDon = thucDon;
	}

}
