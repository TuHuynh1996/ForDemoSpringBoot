package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.AddKhuVucForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.banphong.KhuVuc;

public interface IKhuVucService {

	List<KhuVuc> getAll();

	Sms add(AddKhuVucForm khuVuc);

	Sms delete(String id);

}
