package com.example.demo.JsonClass;

import com.example.demo.model.chungtu.ChungTu;
import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.SanPham;

public class DongCungTuJson {
	private Integer id;
	private ChungTu chungTu;
	private SanPham sanPham;
	private DVTKhac maDVT;
	private double soLuong;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ChungTu getChungTu() {
		return chungTu;
	}
	public void setChungTu(ChungTu chungTu) {
		this.chungTu = chungTu;
	}
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public DVTKhac getMaDVT() {
		return maDVT;
	}
	public void setMaDVT(DVTKhac maDVT) {
		this.maDVT = maDVT;
	}
	public double getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}
	public long getDonGia() {
		return donGia;
	}
	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}
	public String getGioVao() {
		return gioVao;
	}
	public void setGioVao(String gioVao) {
		this.gioVao = gioVao;
	}
	public String getGioRa() {
		return gioRa;
	}
	public void setGioRa(String gioRa) {
		this.gioRa = gioRa;
	}
	public double getTraLai() {
		return traLai;
	}
	public void setTraLai(double traLai) {
		this.traLai = traLai;
	}
	public double getGiam() {
		return giam;
	}
	public void setGiam(double giam) {
		this.giam = giam;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	private long donGia;
	private String gioVao;
	private String gioRa;
	private double traLai;
	private double giam;
	private String ghiChu;
}
