package com.example.demo.JsonClass;

import java.util.List;

import com.example.demo.model.chungtu.DongChungTu;

public class ChungTuJson {
	private String id;
	private String ngayCT;
	private String noiDung;
	private String banPhong;
	private String khachHang;
	private int soKhach;
	private String thuNgan;
	private String nhanVien;
	private long giam;
	private long thueVAT;
	private long phiPV;
	private long soTien;
	private long traTruoc;
	private long conNo;
	private List<DongChungTu> dongChungTu;

	public ChungTuJson(String id, String ngayCT, String noiDung, String banPhong, String khachHang, int soKhach,
			String thuNgan, String nhanVien, long giam, long thueVAT, long phiPV, long soTien, long traTruoc,
			long conNo, List<DongChungTu> dongChungTu) {
		super();
		this.id = id;
		this.ngayCT = ngayCT;
		this.noiDung = noiDung;
		this.banPhong = banPhong;
		this.khachHang = khachHang;
		this.soKhach = soKhach;
		this.thuNgan = thuNgan;
		this.nhanVien = nhanVien;
		this.giam = giam;
		this.thueVAT = thueVAT;
		this.phiPV = phiPV;
		this.soTien = soTien;
		this.traTruoc = traTruoc;
		this.conNo = conNo;
		this.dongChungTu = dongChungTu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNgayCT() {
		return ngayCT;
	}

	public void setNgayCT(String ngayCT) {
		this.ngayCT = ngayCT;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getBanPhong() {
		return banPhong;
	}

	public void setBanPhong(String banPhong) {
		this.banPhong = banPhong;
	}

	public String getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}

	public int getSoKhach() {
		return soKhach;
	}

	public void setSoKhach(int soKhach) {
		this.soKhach = soKhach;
	}

	public String getThuNgan() {
		return thuNgan;
	}

	public void setThuNgan(String thuNgan) {
		this.thuNgan = thuNgan;
	}

	public String getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(String nhanVien) {
		this.nhanVien = nhanVien;
	}

	public long getGiam() {
		return giam;
	}

	public void setGiam(long giam) {
		this.giam = giam;
	}

	public long getThueVAT() {
		return thueVAT;
	}

	public void setThueVAT(long thueVAT) {
		this.thueVAT = thueVAT;
	}

	public long getPhiPV() {
		return phiPV;
	}

	public void setPhiPV(long phiPV) {
		this.phiPV = phiPV;
	}

	public long getSoTien() {
		return soTien;
	}

	public void setSoTien(long soTien) {
		this.soTien = soTien;
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

	public List<DongChungTu> getDongChungTu() {
		return dongChungTu;
	}

	public void setDongChungTu(List<DongChungTu> dongChungTu) {
		this.dongChungTu = dongChungTu;
	}

}
