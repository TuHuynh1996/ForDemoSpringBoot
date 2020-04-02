package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.IService.IBanLeService;

@Controller
public class ChungTuController {
	@Autowired
	IBanLeService banLeService;
	@RequestMapping("/ajax/quanglychungtu/banle/getbanle")
	public ResponseEntity<?> getChungTu(@RequestParam String from, @RequestParam String to, Model model) {
		System.out.println(from+"         "+ to);
		return ResponseEntity.ok(banLeService.getChungTuByDate(from,to));
	}
	
	
}
