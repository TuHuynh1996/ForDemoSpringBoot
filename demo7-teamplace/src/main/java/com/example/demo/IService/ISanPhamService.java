package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.MatHangForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.SanPham;

public interface ISanPhamService {
////////////////////////////////////////////////////
	List<SanPham> getList(String id);

	Sms saveSanPham(MatHangForm sanPhamForm);

	Sms changeFolder(Sms nv);

	SanPham getbyId(String id);

	List<SanPham> getAllThanhPhan();

	List<SanPham> getAllThucDon(String id);

	Sms deleteSanPham(String id);

}
