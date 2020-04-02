package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.IService.IKhachHangService;

@RestController
public class TestController{
	@Autowired
	IKhachHangService khachHangService;

	

//	private Object gets(HttpServletRequest account) {
//		Object name = account.getSession().getAttribute("account");
//		return name;
//	}

	

//	@RequestMapping(value = "/test")
//	public List<KhachHang> getAll() {
//		return khachHangService.getAll();
//	}

	@RequestMapping(value = "/set")
	public Object setSession(HttpServletRequest request) {

		String nameBlog = "Blog IT Phu Tran - Chia sẽ kiến thức lập trình miễn Phí";
		request.getSession().setAttribute("account", nameBlog);
		return request.getSession().getAttribute("account");
	}

	@RequestMapping(value ="/get")
	public Object getSession(HttpServletRequest account) {
		Object name = account.getSession().getAttribute("account");
		return name;
//		}else {
//			return "bbbbbbbbbbbb";
//		}
	}
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request) {
		request.getSession().invalidate();
		return "d";
	}
}
