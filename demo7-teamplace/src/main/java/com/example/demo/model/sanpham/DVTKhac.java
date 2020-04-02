package com.example.demo.model.sanpham;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dvtkhac")
public class DVTKhac {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "masp")
	private SanPham sanPham;
	@ManyToOne
	@JoinColumn(name = "madvt")
	private DonViTinh donViTinh;
	@Column(name = "quidoi")
	private double quiDoi;	
	@Column(name = "ismenu")
	private int isMenu;
	@OneToMany(mappedBy="dvtKhac", cascade = CascadeType.REMOVE)
    private Set<BangGia> bangGia;
	@Transient
	private BangGia bangGiaselected;


	public DVTKhac() {
	};

	public DVTKhac(Integer id, SanPham sanPham, DonViTinh donViTinh, double quiDoi, int isMenu) {
		super();
		this.id = id;
		this.sanPham = sanPham;
		this.donViTinh = donViTinh;
		this.quiDoi = quiDoi;
		this.isMenu = isMenu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SanPham getSanPham() {
		return sanPham;
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

	public double getQuiDoi() {
		return quiDoi;
	}

	public void setQuiDoi(double quiDoi) {
		this.quiDoi = quiDoi;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}
	public 	TreeSet<BangGia> getBangGia() {
//		System.out.println(this.bangGia +" aaaaaaaaaaaaaaaaa");
		TreeSet<BangGia> sortBG = new TreeSet<BangGia>(this.bangGia);
		return sortBG;
	}

	public void setBangGia(Set<BangGia> bangGia) {
		this.bangGia = bangGia;
	}
	
	public BangGia getBangGiaSelected() {
		return bangGiaselected;
	}

	public void setBangGiaSelected(String loaiBangGiaId) {
		BangGia bangGiaselected = null;
		for (BangGia bangGia : this.getBangGia()) {
			if (bangGia.getLoaiBangGia().getId().equals(loaiBangGiaId)) {
				bangGiaselected = bangGia;
			}
		}
		this.bangGiaselected = bangGiaselected;
	}
	
	public long getDonGiaByLoaiBangGia(String loaiBangGiaId) {
		List<BangGia> listBangGia = new ArrayList<BangGia>(this.getBangGia());
		long donGiaByLoaiBangGia = 0;
		for (BangGia bangGia : listBangGia) {
			if (bangGia.getLoaiBangGia().getId().equals(loaiBangGiaId)) {
				donGiaByLoaiBangGia = bangGia.getDonGia() - bangGia.getGiam();
				break;
			}
		}
		return donGiaByLoaiBangGia;
	}


}
