package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.AddBanPhongForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.banphong.BanPhong;

public interface IBanPhongService {

	List<BanPhong> getAll();

	Sms add(AddBanPhongForm banphong);

	Sms delete(String id);

	List<BanPhong> getAllMenu();

}
