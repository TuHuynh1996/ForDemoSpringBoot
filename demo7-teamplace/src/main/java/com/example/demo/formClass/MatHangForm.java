package com.example.demo.formClass;

import java.util.List;

public class MatHangForm {
	private String id;
	private String ten;
	private String maNhom;
	private String dvt;
	private Double sLDK;
	private long gTDK;
	private int isMenu;
	private List<Long> donGia;
	private List<Long> giam;

	public MatHangForm(String id, String ten, String maNhom, String dvt, Double sLDK, long gTDK, int isMenu, List<Long> donGia,
			List<Long> giam) {
		super();
		this.id = id;
		this.ten = ten;
		this.maNhom = maNhom;
		this.dvt = dvt;
		this.sLDK = sLDK;
		this.gTDK = gTDK;
		this.isMenu = isMenu;
		this.donGia = donGia;
		this.giam = giam;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	

	public String getMaNhom() {
		return maNhom;
	}

	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}
	

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public Double getSLDK() {
		return sLDK;
	}

	public void setSLDK(Double sLDK) {
		this.sLDK = sLDK;
	}

	public long getGTDK() {
		return gTDK;
	}

	public void setGTDK(long gTDK) {
		this.gTDK = gTDK;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public List<Long> getDonGia() {
		return donGia;
	}

	public void setDonGia(List<Long> donGia) {
		this.donGia = donGia;
	}

	public List<Long> getGiam() {
		return giam;
	}

	public void setGiam(List<Long> giam) {
		this.giam = giam;
	}
}
