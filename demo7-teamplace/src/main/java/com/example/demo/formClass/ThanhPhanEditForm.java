package com.example.demo.formClass;

public class ThanhPhanEditForm {
	private Integer id;
	private String sanPhamId;
	private String donViTinhId;
	private String thanhPhanId;
	private String dvtThanhPhan;
	private double soLuong;

	public ThanhPhanEditForm(Integer id, String sanPhamId, String donViTinhId, String thanhPhanId, String dvtThanhPhan,
			double soLuong) {
		super();
		this.id = id;
		this.sanPhamId = sanPhamId;
		this.donViTinhId = donViTinhId;
		this.thanhPhanId = thanhPhanId;
		this.dvtThanhPhan = dvtThanhPhan;
		this.soLuong = soLuong;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSanPhanId() {
		return sanPhamId;
	}

	public void setSanPhanId(String sanPhanId) {
		this.sanPhamId = sanPhanId;
	}

	public String getDonViTinhId() {
		return donViTinhId;
	}

	public void setDonViTinhId(String donViTinhId) {
		this.donViTinhId = donViTinhId;
	}

	public String getThanhPhanId() {
		return thanhPhanId;
	}

	public void setThanhPhanId(String thanhPhanId) {
		this.thanhPhanId = thanhPhanId;
	}

	public String getDvtThanhPhan() {
		return dvtThanhPhan;
	}

	public void setDvtThanhPhan(String dvtThanhPhan) {
		this.dvtThanhPhan = dvtThanhPhan;
	}

	public double getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}

}
