package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.model.nhanvien.BoPhan;

public interface IBoPhanService {
///////////////////////////////////////////////////////////
	List<BoPhan> getList();
	
	Sms saveBP(BoPhan boPhan);

	Sms deteleBoPhan(BoPhan boPhan);
}
