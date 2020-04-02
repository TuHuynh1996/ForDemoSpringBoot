package com.example.demo.model.banphong;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.example.demo.model.chungtu.BanLe;
import com.example.demo.model.chungtu.ChungTu;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "sanbanphong")
public class BanPhong {
	@Id
	@Column(name = "Id")
	private String id;
	@ManyToOne
	@JoinColumn(name = "MaKV")
	private KhuVuc maKV;
	@ManyToOne
	@JoinColumn(name = "MaBG")
	private LoaiBangGia maBG;
//	0: trống, 1: chờ khách đặt bàn, 2: không sử dụng
	private int status;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="banPhong")
	@JsonBackReference
	private List<ChungTu> chungTu;
	@Transient
	private BanLe chungTuUsing;

	public BanPhong() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BanPhong(String id, KhuVuc maKV, LoaiBangGia maBG, int status) {
		super();
		this.id = id;
		this.maKV = maKV;
		this.maBG = maBG;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String soBan) {
		this.id = soBan;
	}

	public KhuVuc getMaKV() {
		return maKV;
	}

	public void setMaKV(KhuVuc maKV) {
		this.maKV = maKV;
	}

	public LoaiBangGia getMaBG() {
		return maBG;
	}

	public void setMaBG(LoaiBangGia maBG) {
		this.maBG = maBG;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ChungTu> getChungTu() {
		return chungTu;
	}

	public void setChungTu(List<ChungTu> chungTu) {
		this.chungTu = chungTu;
	}

	public BanLe getChungTuUsing() {
		return chungTuUsing;
	}

	public void setChungTuUsing(BanLe chungTuUsing) {
		this.chungTuUsing = chungTuUsing;
	}
}