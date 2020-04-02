package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.IService.IKhachHangService;
import com.example.demo.IService.INhomKhachService;
import com.example.demo.formClass.KhachHangForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.khachhang.NhomKhach;

@Controller
public class KhachHangController {
	@Autowired
	INhomKhachService nhomKhachService;
	@Autowired
	IKhachHangService khachHangService;

	@RequestMapping(value = "/ajax/khachhang/nhomkhach")
	public String nhomKhach(Model model) {
		model.addAttribute("nhomkhach", nhomKhachService.getList());
		return "content/khachhang/nhomkhachhang";
	}

	@RequestMapping(value = "/ajax/khachhang/listkhach/{id}/", method = RequestMethod.GET)
	public String listKhach(@PathVariable("id") String id, Model model) {
		model.addAttribute("listkhach", khachHangService.getListKhach(id));
		return "content/khachhang/listkhachhang";
	}

	@RequestMapping(value = "/ajax/khachhang/savekh")
	public ResponseEntity<?> saveKhachHang(@RequestBody KhachHangForm khachHangForm) {
		Sms sms = khachHangService.saveKhach(khachHangForm);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/khachhang/savenhomkhach")
	public ResponseEntity<?> saveNhomKhach(@RequestBody NhomKhach nhomKhach) {
		Sms sms = nhomKhachService.saveNhomKhach(nhomKhach);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/khachhang/deletenhomkhach")
	public ResponseEntity<?> deleteNhomKhach(@RequestBody NhomKhach nhomKhach) {
		Sms sms = nhomKhachService.deteleNhomKhach(nhomKhach);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/khachhang/chuyennhomkhach", method = RequestMethod.POST)
	public ResponseEntity<?> changeBoPhan(@RequestBody Sms nhanVien) {
		Sms sms = khachHangService.changeFolder(nhanVien);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}
	
	@RequestMapping(value = "/ajax/khachhang/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteBoPhan(@RequestBody Sms nhanVien) {
		Sms sms = khachHangService.deleteBoPhan(nhanVien.getId());
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}
}
