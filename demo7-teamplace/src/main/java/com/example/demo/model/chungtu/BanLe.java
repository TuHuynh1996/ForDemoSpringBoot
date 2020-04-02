package com.example.demo.model.chungtu;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.example.demo.model.banphong.BanPhong;
import com.example.demo.model.khachhang.KhachHang;
import com.example.demo.model.nhanvien.NhanVien;

@Entity
@DiscriminatorValue("2")
public class BanLe extends ChungTu {
	@ManyToOne(optional = true)
	private KhachHang khachHang;
	private int soKhach;
	@ManyToOne
	private NhanVien thuNgan;
	@ManyToOne(optional = true)
	private NhanVien nhanVien;
	private long giam;
	private long thueVAT;
	private long phiPV;
	private long soTien;
	private long traTruoc;
	private long conNo;

	public BanLe() {
	}

	public BanLe(String id, Date ngayCT, String noiDung, int trangThai, BanPhong banPhong, KhachHang khachHang,
			int soKhach, NhanVien thuNgan, NhanVien nhanVien, long giam, long thueVAT, long phiPV, long soTien,
			long traTruoc, long conNo) {
		super(id, ngayCT, banPhong, noiDung, trangThai);
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
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhacHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public int getSoKhach() {
		return soKhach;
	}

	public void setSoKhach(int soKhach) {
		this.soKhach = soKhach;
	}

	public NhanVien getThuNgan() {
		return thuNgan;
	}

	public void setThuNgan(NhanVien thuNgan) {
		this.thuNgan = thuNgan;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
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

	public String getKhoangThoiGian() {
		if (this.getTrangThai() == 0) {
			Date d1 = this.getNgayCT();
			Date d2 = new Date();
			long diff = d2.getTime() - d1.getTime();
			long diffMinutes = diff / (60 * 1000);
			return diffMinutes+" phút";
		}else {
			return null;
		}
	}
}
