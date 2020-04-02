package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.formClass.DatBanForm.DatBanForm;
import com.example.demo.model.chungtu.DonDatHang;

public interface IDonDatHangService {

	List<DonDatHang> getAll();

	Sms save(DatBanForm datBan);

	Sms delete(Sms sms);


}
