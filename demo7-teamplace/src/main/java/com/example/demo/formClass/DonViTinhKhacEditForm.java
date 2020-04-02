package com.example.demo.formClass;

import java.util.List;

public class DonViTinhKhacEditForm {
	private Integer id;
	private String sanPhamId;
	private String donViTinh;
	private double quiDoi;
	private int isMenu;
	private List<Long> donGia;
	private List<Long> giam;

	public DonViTinhKhacEditForm(Integer id, String sanPhamId, String donViTinh, double quiDoi, int isMenu,
			List<Long> donGia, List<Long> giam) {
		super();
		this.id = id;
		this.sanPhamId = sanPhamId;
		this.donViTinh = donViTinh;
		this.quiDoi = quiDoi;
		this.isMenu = isMenu;
		this.donGia = donGia;
		this.giam = giam;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSanPhamId() {
		return sanPhamId;
	}

	public void setSanPhamId(String sanPhamId) {
		this.sanPhamId = sanPhamId;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
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
