package com.example.demo.IService;

import com.example.demo.formClass.Sms;
import com.example.demo.formClass.ThanhPhanEditForm;
import com.example.demo.formClass.ThanhPhanForm;

public interface IThanhPhanService {

	Sms saveThanhPhan(ThanhPhanForm tp);

	Sms deleteById(Integer id);

	ThanhPhanEditForm edit(ThanhPhanEditForm tp);


}
