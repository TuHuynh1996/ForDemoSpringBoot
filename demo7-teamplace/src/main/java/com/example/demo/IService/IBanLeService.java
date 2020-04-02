package com.example.demo.IService;

import java.util.List;

import com.example.demo.formClass.Sms;
import com.example.demo.formClass.DatBanForm.BanLeForm;
import com.example.demo.model.banphong.BanPhong;
import com.example.demo.model.chungtu.BanLe;
import com.example.demo.model.nhanvien.NhanVien;
import com.example.demo.model.sanpham.SanPham;

public interface IBanLeService {

	Sms save(BanLeForm banLeForm);

	List<SanPham> getThucDon(String id);

	Sms delete(Sms sms);

	Sms change(Sms sms);

	BanLe getById(String id);

	Sms thanhtoan(Sms sms);

	Sms graft(Sms sms);

	Sms thanhToanDatHang(Sms sms);

	List<BanPhong> getBanTrong();

	Sms vaoBan(Sms sms, NhanVien thuNgan);

	List<BanLe> getChungTuByDate(String from, String to);

}
