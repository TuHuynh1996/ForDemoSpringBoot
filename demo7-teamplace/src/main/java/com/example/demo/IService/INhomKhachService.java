package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.model.khachhang.NhomKhach;

public interface INhomKhachService {
///////////////////////////////////////////////////
	List<NhomKhach> getList();

	Sms saveNhomKhach(NhomKhach nhomKhach);

	Sms deteleNhomKhach(NhomKhach nhomKhach);

}
