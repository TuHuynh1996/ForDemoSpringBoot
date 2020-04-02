package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.DoiMatKhau;
import com.example.demo.formClass.LoginForm;
import com.example.demo.formClass.NhanVienForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.nhanvien.NhanVien;

public interface INhanVienService {
	NhanVien GetNBByAccount(String name, String pass);

	///////////////////////////////////////////////////////////////////
	DoiMatKhau changePassword(DoiMatKhau matKhau, NhanVien nv);

	List<NhanVien> listNhanVien(String bp);

	Sms saveNhanVien(NhanVienForm nvForm);

	Sms changeFolder(Sms nv);

	LoginForm logIn();

	Sms deleteNhanVien(String id);
}
