package com.example.demo.model.nhanvien;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bophan")
public class BoPhan {
	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Macha")
	private String maCha;
	@Column(name = "Tenbp")
	private String tenBP;
	
	@javax.persistence.Transient
	private List<BoPhan> con;

	public BoPhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoPhan(String id, String maCha, String tenBP) {
		super();
		this.id = id;
		this.maCha = maCha;
		this.tenBP = tenBP;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaCha() {
		return maCha;
	}

	public void setMaCha(String maCha) {
		this.maCha = maCha;
	}

	public String getTenBP() {
		return tenBP;
	}

	public void setTenBP(String tenBP) {
		this.tenBP = tenBP;
	}

	public List<BoPhan> getCon() {
		return con;
	}

	public void setCon(List<BoPhan> con) {
		List<BoPhan> children = new ArrayList<BoPhan>();
		for(int i = 0; i < con.size(); i++) {
			if(con.get(i).getMaCha().equals(this.id)) {
				children.add(con.get(i));
			}
		}
		this.con = children;
	}


}