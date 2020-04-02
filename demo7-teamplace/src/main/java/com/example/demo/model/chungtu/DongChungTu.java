package com.example.demo.model.chungtu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.DonViTinh;
import com.example.demo.model.sanpham.SanPham;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dongchungtu")
public class DongChungTu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "chungtu")
	private ChungTu chungTu;
	@JsonIgnore
	@ManyToOne(optional = true)
	@JoinColumn(name = "sanpham")
	private SanPham sanPham;
	@ManyToOne(optional = true)
	@JoinColumn(name = "madvt")
	private DVTKhac maDVT;
	private double soLuong;
	private long donGia;
	private String gioVao;
	private String gioRa;
	private double traLai;
	private long giam;
	private String ghiChu;

	public DongChungTu() {
	}

	public DongChungTu(Integer id, ChungTu chungTu, SanPham sanPham, double soLuong, long donGia, String gioVao,
			String gioRa, double traLai, long giam, String ghiChu) {
		super();
		this.id = id;
		this.chungTu = chungTu;
		this.sanPham = sanPham;
//		this.maDVT = sanPham.getDonViTinh().getTen();
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
		this.traLai = traLai;
		this.giam = giam;
		this.ghiChu = ghiChu;
	}

	public DongChungTu(Integer id, ChungTu chungTu, DVTKhac maDVT, double soLuong, long donGia, String gioVao,
			String gioRa, double traLai, long giam, String ghiChu) {
		super();
		this.id = id;
		this.chungTu = chungTu;
//		this.sanPham = sanPham;
		this.maDVT = maDVT;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
		this.traLai = traLai;
		this.giam = giam;
		this.ghiChu = ghiChu;
	}

	public String getName() {
		if(maDVT == null) {
			return sanPham.getId();
		}else {
			return "dvtkhac-"+maDVT.getId();
		}
	}

	public void setName() {

	}

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
		if (sanPham == null) {
			return maDVT.getSanPham();
		}
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DonViTinh getMaDVT() {
		if (maDVT == null) {
			return sanPham.getDonViTinh();
		}
		return maDVT.getDonViTinh();
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

	public long getGiam() {
		return giam;
	}

	public void setGiam(long giam) {
		this.giam = giam;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	public long resetTien(String loaiBangGiaId) {
		if (sanPham == null) {
			return maDVT.getDonGiaByLoaiBangGia(loaiBangGiaId);
		}
		return sanPham.getDonGiaByLoaiBangGia(loaiBangGiaId);
	}
}