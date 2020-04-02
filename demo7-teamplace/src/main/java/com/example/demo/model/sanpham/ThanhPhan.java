package com.example.demo.model.sanpham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "thanhphan")
public class ThanhPhan {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "masp")
	private SanPham sanPham;
	@ManyToOne
	@JoinColumn(name = "madvt")
	private DonViTinh donViTinh;
	@ManyToOne
	@JoinColumn(name = "matp")
	private SanPham thanhPhan;
	@ManyToOne
	@JoinColumn(name = "madvttp")
	private DonViTinh dvtThanhPhan;
	@Column(name = "soluong")
	private double soLuong;

	public ThanhPhan() {
	}

	public ThanhPhan(Integer id, SanPham sanPham, DonViTinh donViTinh, SanPham thanhPhan, DonViTinh dvtThanhPhan,
			double soLuong) {
		super();
		this.id = id;
		this.sanPham = sanPham;
		this.donViTinh = donViTinh;
		this.thanhPhan = thanhPhan;
		this.dvtThanhPhan = dvtThanhPhan;
		this.soLuong = soLuong;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSanPham() {
		return sanPham.getId();
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public DonViTinh getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(DonViTinh donViTinh) {
		this.donViTinh = donViTinh;
	}

	public SanPham getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(SanPham thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	public DonViTinh getDvtThanhPhan() {
		return dvtThanhPhan;
	}

	public void setDvtThanhPhan(DonViTinh dvtThanhPhan) {
		this.dvtThanhPhan = dvtThanhPhan;
	}

	public double getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}

}
