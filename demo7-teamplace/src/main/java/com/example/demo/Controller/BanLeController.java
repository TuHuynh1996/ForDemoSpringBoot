package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.IService.IBanLeService;
import com.example.demo.IService.IDongChungTuService;
import com.example.demo.IService.IKhachHangService;
import com.example.demo.IService.ILoaiBanGiaService;
import com.example.demo.IService.INhanVienService;
import com.example.demo.formClass.Sms;
import com.example.demo.formClass.DatBanForm.BanLeForm;
import com.example.demo.model.chungtu.BanLe;

@Controller
public class BanLeController {
	@Autowired
	IBanLeService banLeService;
	@Autowired
	IKhachHangService khachHangService;
	@Autowired
	ILoaiBanGiaService loaiBanGiaService;
	@Autowired
	INhanVienService nhanVienService;
	@Autowired
	IDongChungTuService dongChungTuService;

	@RequestMapping(value = "/ajax/thungan/banle/getthucdon/{id}")
	public String getThucDon(@PathVariable String id, Model model) {
		model.addAttribute("thucdon", banLeService.getThucDon(id));
		return "/content/thungan/banphong/thucdon";
	}

	@RequestMapping(value = "/ajax/thungan/banle/thucdon")
	public String thucDonDaDat(@RequestParam String id, @RequestParam String bangGiaId, Model model) {
		model.addAttribute("chungtu", dongChungTuService.getDongChungTuByChungTuIdBangGia(id, bangGiaId));
		return "/content/thungan/banphong/thucdondagoi";
	}

	@RequestMapping(value = "/ajax/thungan/banle/thongtin")
	public String thongTin(Model model) {
		model.addAttribute("khachhang", khachHangService.getListKhach("0"));
		model.addAttribute("banggia", loaiBanGiaService.getAll());
		model.addAttribute("nhanvien", nhanVienService.listNhanVien("0"));
		return "/content/thungan/banphong/thongtinbanle";
	}

	@RequestMapping(value = "/ajax/thungan/banle/hoadon/{id}")
	public String hoaDon(Model model, @PathVariable("id") String id) {
		BanLe banLe = banLeService.getById(id);
		if (banLe != null) {
			model.addAttribute("chungTu", banLeService.getById(id));
		}
		return "/content/thungan/banphong/hoadon";
	}

	@RequestMapping(value = "/ajax/thungan/banle/save")
	public ResponseEntity<?> save(@RequestBody BanLeForm banLeForm) {
		Sms sms = banLeService.save(banLeForm);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/thungan/banle/delete")
	public ResponseEntity<?> delete(@RequestBody Sms sms) {
		Sms sms1 = banLeService.delete(sms);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/thungan/banle/change")
	public ResponseEntity<?> change(@RequestBody Sms sms) {
		Sms sms1 = banLeService.change(sms);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/thungan/banle/thanhtoan")
	public ResponseEntity<?> save(@RequestBody Sms sms) {
		Sms sms1 = banLeService.thanhtoan(sms);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/thungan/banle/ghepban")
	public ResponseEntity<?> graft(@RequestBody Sms sms) {
		Sms sms1 = banLeService.graft(sms);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}

}
