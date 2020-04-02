package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.DonViTinh;

public interface IDonViTinhService {

	List<DonViTinh> getAll();

	DonViTinh getById(String id);

	Sms saveDVT(DonViTinh dvt);

	Sms deteleDVT(DonViTinh dvt);

}
