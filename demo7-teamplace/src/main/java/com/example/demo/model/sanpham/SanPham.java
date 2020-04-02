package com.example.demo.model.sanpham;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sanpham")
public class SanPham {
	@Id
	@Column(name = "id")
	private String id;
	@ManyToOne
	@JoinColumn(name = "manhom")
	private NhomHang nhomHang;
	@Column(name = "tensp")
	private String tenSP;
	@ManyToOne
	@JoinColumn(name = "madvt")
	private DonViTinh donViTinh;
	@Column(name = "sldk")
	private double SLDK;
	@Column(name = "gtdk")
	private long GTDK;
	@Column(name = "ismenu")
	private int isMenu;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "sanPham")
	private Set<BangGia> bangGia;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanPham")
	private Set<ThanhPhan> thanhPhan;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sanPham")
	private Set<DVTKhac> dvtKhac;
	@Transient
	private BangGia bangGiaselected;

	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPham(String id, NhomHang nhomHang, String tenSP, DonViTinh donViTinh, double sLDK, long gTDK,
			int isMenu) {
		super();
		this.id = id;
		this.nhomHang = nhomHang;
		this.tenSP = tenSP;
		this.donViTinh = donViTinh;
		SLDK = sLDK;
		GTDK = gTDK;
		this.isMenu = isMenu;
	}

	public String getId() {
		return id;
	}

	public void setId(String maSP) {
		this.id = maSP;
	}

	public NhomHang getNhomHang() {
		return nhomHang;
	}

	public void setNhomHang(NhomHang nhomHang) {
		this.nhomHang = nhomHang;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public DonViTinh getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(DonViTinh donViTinh) {
		this.donViTinh = donViTinh;
	}

	public double getSLDK() {
		return SLDK;
	}

	public void setSLDK(double sLDK) {
		this.SLDK = sLDK;
	}

	public long getGTDK() {
		return GTDK;
	}

	public void setGTDK(long gTDK) {
		this.GTDK = gTDK;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public TreeSet<BangGia> getBangGia() {
		TreeSet<BangGia> sortBG = new TreeSet<BangGia>(bangGia);
		return sortBG;
//		return bangGia;
	}

	public void setBangGia(Set<BangGia> bangGia) {
		this.bangGia = bangGia;
	}

	public Set<ThanhPhan> getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(Set<ThanhPhan> thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	public Set<DVTKhac> getDvtKhac() {
		return dvtKhac;
	}

	public void setDvtKhac(Set<DVTKhac> dvtKhac) {
		this.dvtKhac = dvtKhac;
	}

	public List<DVTKhac> getDvtKhacThucDon() {
		List<DVTKhac> dvtKhac1 = new ArrayList<DVTKhac>();
		for (DVTKhac it : this.getDvtKhac()) {
			if (it.getIsMenu() == 1) {
				dvtKhac1.add(it);
			}
		}
		return dvtKhac1;
	}

	public void setDvtKhacThucDon(Set<DVTKhac> dvtKhac) {
		this.dvtKhac = dvtKhac;
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
			}
		}
		return donGiaByLoaiBangGia;
	}

}