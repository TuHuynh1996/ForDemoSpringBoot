package com.example.demo.IService;

import com.example.demo.formClass.AddDonViTinhKhacForm;
import com.example.demo.formClass.DonViTinhKhacEditForm;
import com.example.demo.formClass.Sms;

public interface IDVTKhacService {

	Sms add(AddDonViTinhKhacForm donViTinhForm);

	Sms delete(Integer id);

	Sms edit(DonViTinhKhacEditForm donViTinhKhacEditForm);

}
