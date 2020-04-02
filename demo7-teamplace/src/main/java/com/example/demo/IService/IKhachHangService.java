package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.KhachHangForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.khachhang.KhachHang;

public interface IKhachHangService {

////////////////////////////////////////////////////

	List<KhachHang> getListKhach(String id);

	Sms saveKhach(KhachHangForm khachHangForm);

	Sms changeFolder(Sms nhanVien);

	Sms deleteBoPhan(String id);

}
