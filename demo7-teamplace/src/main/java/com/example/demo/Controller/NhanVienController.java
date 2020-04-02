package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.IService.IBoPhanService;
import com.example.demo.IService.INhanVienService;
import com.example.demo.formClass.DoiMatKhau;
import com.example.demo.formClass.NhanVienForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.nhanvien.BoPhan;
import com.example.demo.model.nhanvien.NhanVien;

@Controller
public class NhanVienController {
	@Autowired
	INhanVienService nhanVienService;
	@Autowired
	IBoPhanService boPhanService;

	@RequestMapping(value = "/ajax/doimatkhau", method = RequestMethod.POST)
	public ResponseEntity<?> doimatkhau(@RequestBody DoiMatKhau matKhau, HttpServletRequest request) {
		NhanVien nhanVien = (NhanVien) request.getSession().getAttribute("account");
		return ResponseEntity.ok(nhanVienService.changePassword(matKhau, nhanVien));
	}

	@RequestMapping(value = "/ajax/nhanvien/listbophan")
	public String boPhan(Model model) {
		model.addAttribute("bophan", boPhanService.getList());
		return "/content/nhanvien/listbophan";
	}

	@RequestMapping(value = "/ajax/nhanvien/listnhanvien/{id}/", method = RequestMethod.GET)
	public String listNhanVien(@PathVariable("id") String boPhan, Model model) {
		model.addAttribute("nhanvien", nhanVienService.listNhanVien(boPhan));
		return "/content/nhanvien/listnhanvien";
	}

	@RequestMapping(value = "/ajax/nhanvien/savenhanvien", method = RequestMethod.POST)
	public ResponseEntity<?> saveNhanVien(@RequestBody NhanVienForm nvForm) {
		Sms sms = nhanVienService.saveNhanVien(nvForm);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/nhanvien/savebophan", method = RequestMethod.POST)
	public ResponseEntity<?> savebp(@RequestBody BoPhan boPhan) {
		Sms sms = boPhanService.saveBP(boPhan);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/nhanvien/deletebophan", method = RequestMethod.POST)
	public ResponseEntity<?> deleteBoPhan(@RequestBody BoPhan boPhan) {
		Sms sms = boPhanService.deteleBoPhan(boPhan);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/nhanvien/chuyenbophan", method = RequestMethod.POST)
	public ResponseEntity<?> deleteNhanVien(@RequestBody Sms nhanVienId) {
		Sms sms = nhanVienService.deleteNhanVien(nhanVienId.getId());
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

}
