package com.example.demo.formClass;

import java.util.ArrayList;
import java.util.List;

public class AddDonViTinhKhacForm {
	private String sanPhamId;
	private List<String> maDonViTinh;
	private List<Double> quiDoi;
	private List<Integer> isMenu;
	private List<ArrayList<Long>> donGia;
	private List<ArrayList<Long>> giam;

	public AddDonViTinhKhacForm(String sanPhamId, List<String> maDonViTinh, List<Double> quiDoi, List<Integer> isMenu,
			List<ArrayList<Long>> donGia, List<ArrayList<Long>> giam) {
		super();
		this.sanPhamId = sanPhamId;
		this.maDonViTinh = maDonViTinh;
		this.quiDoi = quiDoi;
		this.isMenu = isMenu;
		this.donGia = donGia;
		this.giam = giam;
	}

	public String getSanPhamId() {
		return sanPhamId;
	}

	public void setSanPhamId(String sanPhamId) {
		this.sanPhamId = sanPhamId;
	}

	public List<String> getMaDonViTinh() {
		return maDonViTinh;
	}

	public void setMaDonViTinh(List<String> maDonViTinh) {
		this.maDonViTinh = maDonViTinh;
	}

	public List<Double> getQuiDoi() {
		return quiDoi;
	}

	public void setQuiDoi(List<Double> quiDoi) {
		this.quiDoi = quiDoi;
	}

	public List<Integer> getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(List<Integer> isMenu) {
		this.isMenu = isMenu;
	}

	public List<ArrayList<Long>> getDonGia() {
		return donGia;
	}

	public void setDonGia(List<ArrayList<Long>> donGia) {
		this.donGia = donGia;
	}

	public List<ArrayList<Long>> getGiam() {
		return giam;
	}

	public void setGiam(List<ArrayList<Long>> giam) {
		this.giam = giam;
	}

}
