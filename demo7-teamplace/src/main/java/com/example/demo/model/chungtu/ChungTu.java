package com.example.demo.model.chungtu;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.model.banphong.BanPhong;


@Entity
@Table(name = "chungtu")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "loai", discriminatorType = DiscriminatorType.INTEGER)
public class ChungTu {
	@Id
	@Column(name = "id")
	private String id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ngayCT;
	@ManyToOne(optional = true)
	private BanPhong banPhong;
//	@ManyToOne
//	private KhachHang khacHang;
//	private int soKhach;
	private String noiDung;
//	@ManyToOne
//	private NhanVien thuNgan;
//	@ManyToOne
//	private NhanVien nhanVien;
//	private double giam;
//	private double thueVAT;
//	private double phiPV;
//	private double soTien;
//	private double traTruoc;
//	private double conNo;
//	private Date ngayDat;
	private int trangThai;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="chungTu")
	private Set<DongChungTu> dongChungTu;

	public ChungTu() {
		super();
	}

	public ChungTu(String id, Date ngayCT, String noiDung, int trangThai) {
		super();
		this.id = id;
		this.ngayCT = ngayCT;
		this.noiDung = noiDung;
		this.trangThai = trangThai;
	}

	public ChungTu(String id, Date ngayCT, BanPhong banPhong, String noiDung, int trangThai) {
		super();
		this.id = id;
		this.ngayCT = ngayCT;
		this.banPhong = banPhong;
		this.noiDung = noiDung;
		this.trangThai = trangThai;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getNgayCT() {
		return ngayCT;
	}

	public void setNgayCT(Date ngayCT) {
		this.ngayCT = ngayCT;
	}

	public BanPhong getBanPhong() {
		return banPhong;
	}

	public void setBanPhong(BanPhong banPhong) {
		this.banPhong = banPhong;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public Set<DongChungTu> getDongChungTu() {
		return dongChungTu;
	}

	public void setDongChungTu(Set<DongChungTu> dongChungTu) {
		this.dongChungTu = dongChungTu;
	}

}