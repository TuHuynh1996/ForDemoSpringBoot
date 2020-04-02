package com.example.demo.model.sanpham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "banggia")
public class BangGia implements Comparable<BangGia> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Masp", unique = true, nullable = true)
	private SanPham sanPham;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "masp2", unique = true, nullable = true)
	private DVTKhac dvtKhac;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Mabg")
	private LoaiBangGia loaiBangGia;
	@Column(name = "Dongia")
	private long donGia;
	@Column(name = "Giam")
	private long giam;
	
	public BangGia() {
		super();
	}

	public BangGia(Integer id, SanPham sanPham, LoaiBangGia loaiBangGia, long donGia, long giam) {
		super();
		this.id = id;
		this.sanPham = sanPham;
		this.dvtKhac = null;
		this.loaiBangGia = loaiBangGia;
		this.donGia = donGia;
		this.giam = giam;
	}

	public BangGia(Integer id, DVTKhac dvtKhac, LoaiBangGia loaiBangGia, long donGia, long giam) {
		super();
		this.id = id;
		this.sanPham = null;
		this.dvtKhac = dvtKhac;
		this.loaiBangGia = loaiBangGia;
		this.donGia = donGia;
		this.giam = giam;
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

	public int getDvtKhac() {
		return dvtKhac.getId();
	}

	public void setDvtKhac(DVTKhac dvtKhac) {
		this.dvtKhac = dvtKhac;
	}

	public LoaiBangGia getLoaiBangGia() {
		return loaiBangGia;
	}

	public void setLoaiBangGia(LoaiBangGia loaiBangGia) {
		this.loaiBangGia = loaiBangGia;
	}

	public long getDonGia() {
		return donGia;
	}

	public void setDonGia(long donGia) {
		this.donGia = donGia;
	}

	public long getGiam() {
		return giam;
	}

	public void setGiam(long giam) {
		this.giam = giam;
	}

	public int compareTo(BangGia o) {
		return this.getLoaiBangGia().getId().compareTo(o.getLoaiBangGia().getId());
	}

}