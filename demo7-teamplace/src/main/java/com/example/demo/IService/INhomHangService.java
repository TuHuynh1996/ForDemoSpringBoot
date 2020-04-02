package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.NhomHang;

public interface INhomHangService {

////////////////////////////////////////////////////
	List<NhomHang> getList();

	Sms saveNhomHang(NhomHang nhomHang);

	Sms deteleNhomHang(NhomHang nhomHang);

}
