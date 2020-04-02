package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.IService.IBanLeService;
import com.example.demo.IService.IDonDatHangService;
import com.example.demo.IService.IDongChungTuService;
import com.example.demo.IService.IKhachHangService;
import com.example.demo.IService.ILoaiBanGiaService;
import com.example.demo.IService.ISanPhamService;
import com.example.demo.formClass.Sms;
import com.example.demo.formClass.DatBanForm.DatBanForm;
import com.example.demo.model.nhanvien.NhanVien;

@Controller
public class DatBanController {
	@Autowired
	IDonDatHangService donDatHangService;
	@Autowired
	ISanPhamService sanPhamService;
	@Autowired
	IKhachHangService khachHangService;
	@Autowired
	ILoaiBanGiaService loaiBanGiaService;
	@Autowired
	IDongChungTuService dongChungTuService;
	@Autowired
	IBanLeService banLeService;

	@RequestMapping(value = "/ajax/thungan/datban/listdatban")
	public String listDatHang(Model model) {
		model.addAttribute("datban", donDatHangService.getAll());
		return "/content/thungan/datban/listdatban";
	}

	@RequestMapping(value = "/ajax/thungan/datban/getthucdon/{id}")
	public String listThucDon(@PathVariable("id") String id, Model model) {
		model.addAttribute("thucdon", sanPhamService.getAllThucDon(id));
		return "/content/thungan/datban/thucdon";
	}

	@RequestMapping(value = "/ajax/thungan/datban/thongtin")
	public String thongTin(Model model) {
		model.addAttribute("khachhang", khachHangService.getListKhach("0"));
		model.addAttribute("banggia", loaiBanGiaService.getAll());
		return "/content/thungan/datban/thongtin";
	}

	@RequestMapping(value = "/ajax/thungan/datban/thucdon/{id}")
	public String thucDonDaDat(@PathVariable("id") String id, Model model) {
		model.addAttribute("chungtu", dongChungTuService.getDongChungTuByChungTuId(id));
		return "/content/thungan/datban/thucdondathang";
	}

	@RequestMapping(value = "/ajax/thungan/datban/add")
	public ResponseEntity<?> addDatHang(@RequestBody DatBanForm datBan) {
		Sms sms = donDatHangService.save(datBan);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}
	@RequestMapping(value = "/ajax/thungan/datban/delete")
	public ResponseEntity<?> deleteDatHang(@RequestBody Sms sms) {
		Sms sms1 = donDatHangService.delete(sms);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/thungan/datban/thanhToan")
	public ResponseEntity<?> thanhToanDatHang(@RequestBody Sms sms) {
		Sms sms1 = banLeService.thanhToanDatHang(sms);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}
	@RequestMapping(value = "/ajax/thungan/datban/bantrong")
	public ResponseEntity<?> getBanTrong(@RequestBody Sms sms) {
		return ResponseEntity.ok(banLeService.getBanTrong());
	}
	
	@RequestMapping(value = "/ajax/thungan/datban/vaoban")
	public ResponseEntity<?> vaoBan(@RequestBody Sms sms, HttpServletRequest request) {
		System.out.println(sms.getId() + "            " + sms.getStatus());
		NhanVien thuNgan = (NhanVien) request.getSession().getAttribute("account");
		Sms sms1 = banLeService.vaoBan(sms, thuNgan);
		return new ResponseEntity<Sms>(sms1, sms1.getStatusHttp());
	}
}
