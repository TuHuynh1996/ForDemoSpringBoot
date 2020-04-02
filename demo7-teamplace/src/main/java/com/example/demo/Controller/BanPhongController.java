package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.IService.IBanPhongService;
import com.example.demo.IService.IKhuVucService;
import com.example.demo.IService.ILoaiBanGiaService;
import com.example.demo.formClass.AddBanPhongForm;
import com.example.demo.formClass.AddKhuVucForm;
import com.example.demo.formClass.Sms;

@Controller
public class BanPhongController {
	@Autowired
	IBanPhongService banPhongService;
	@Autowired
	ILoaiBanGiaService loaiBanGiaService;
	@Autowired
	IKhuVucService khuVucService;

	@RequestMapping(value = "/ajax/banphong/listbanphong")
	public String listBanPhong(Model model) {
		model.addAttribute("banphong", banPhongService.getAll());
		model.addAttribute("banggia", loaiBanGiaService.getAll());
		model.addAttribute("khuvuc", khuVucService.getAll());
		return "/content/thungan/banphong/listbanphong";
	}

	@RequestMapping(value = "/ajax/banphong/listkhuvuc")
	public String listKhuVuc(Model model) {
		model.addAttribute("khuvuc", khuVucService.getAll());
		return "/content/thungan/banphong/listkhuvuc";
	}

	@RequestMapping(value = "/ajax/banphong/listbanphong/add")
	public ResponseEntity<?> addBanPhong(@RequestBody AddBanPhongForm banPhong) {
		Sms sms = banPhongService.add(banPhong);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/banphong/listbanphong/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteBanPhong(@RequestBody Sms id) {
		Sms sms = banPhongService.delete(id.getId());
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/banphong/listkhuvuc/add")
	public ResponseEntity<?> addKhuVuc(@RequestBody AddKhuVucForm khuVuc) {
		Sms sms =  khuVucService.add(khuVuc);
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/banphong/listkhuvuc/delete", method = RequestMethod.POST)
	public ResponseEntity<?> deleteKhuVuc(@RequestBody Sms id) {
		Sms sms = khuVucService.delete(id.getId());
		return new ResponseEntity<Sms>(sms, sms.getStatusHttp());
	}

	/*
	 * 
	 * Thao tác thu ngân
	 * 
	 */
	@RequestMapping(value = "/ajax/banphong/thungan1")
	public String thuNgan1(Model model) {
		model.addAttribute("banphong", banPhongService.getAllMenu());
		return "/content/thungan/banphong/thungan1";
	}

}
