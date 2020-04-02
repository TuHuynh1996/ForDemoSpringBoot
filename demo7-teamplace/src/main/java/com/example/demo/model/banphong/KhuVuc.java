package com.example.demo.model.banphong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khuvuc")
public class KhuVuc {
	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "TenKV")
	private String tenKV;
	public KhuVuc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KhuVuc(String id, String tenKV) {
		super();
		this.id = id;
		this.tenKV = tenKV;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenKV() {
		return tenKV;
	}
	public void setTenKV(String tenKV) {
		this.tenKV = tenKV;
	}

}