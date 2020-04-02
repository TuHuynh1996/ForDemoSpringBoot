package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.LoaiBangGia;

public interface ILoaiBanGiaService {
	List<LoaiBangGia> getAll();

	Sms edit(LoaiBangGia lbg);

	Sms add(LoaiBangGia lbg);

	Sms delete(LoaiBangGia lbg);
}
