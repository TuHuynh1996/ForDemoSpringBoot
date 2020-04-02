package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.IService.INhanVienService;
import com.example.demo.formClass.LoginForm;
import com.example.demo.model.nhanvien.NhanVien;

@Controller
public class MainController {

	@Autowired
	INhanVienService nhanVienService;

	@RequestMapping(value = "/")
	public String demo() {
		return "redirect:/home";
	}
	
	@RequestMapping(value = {"/{unexpect}","/home/{unexpect}"})
	public String unexpect() {
		return "redirect:/home";
	}

	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest request) {
		NhanVien account = (NhanVien) request.getSession().getAttribute("account");
		if(account != null) {
			NhanVien nhanVien = nhanVienService.GetNBByAccount(account.getTenTK(), account.getMatKhau());
			if (nhanVien != null) {
				return "redirect:/home";
			} 
		}
		model.addAttribute("login", nhanVienService.logIn());
		return "other/login";
	}

	@RequestMapping(value = "/checklogin", method = RequestMethod.POST)
	public String checklogin(@ModelAttribute("login") LoginForm login, HttpServletRequest request, RedirectAttributes redirect) {
		String name = login.getName();
		String pass = login.getPassword();
		
//		String password = "stackjava.com";
//		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
//		System.out.println("BCrypt hash: " + hash);
		
		NhanVien nhanVien = nhanVienService.GetNBByAccount(name, pass);

		if (nhanVien != null) {
			request.getSession().setAttribute("account", nhanVien);
			return "redirect:/home";
		} else {
			redirect.addFlashAttribute("fail", "Sai mật khẩu hoặc tên người dùng!");
			return "redirect:/login";
		}
	}
	@RequestMapping(value = "/dangxuat")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login";
	}
	@RequestMapping(value = "/home")
	public String page(HttpServletRequest request) {
		NhanVien nv = (NhanVien) request.getSession().getAttribute("account");
		if (nv.getIsThuNgan() == 1) {
			return "redirect:/home/thungan/banphong";
		} else if (nv.getIsKeToan() == 1) {
			return "redirect:/home/baocaothongke/baocaodoanhthu";
		} else {
			return "redirect:/login";
		}
	}
//	@RequestMapping(value = "/home/{a}/{b}")
//	public String pagep(HttpServletRequest request) {
//		return "layout/index";
//	}

	@RequestMapping(value = { "/home/thungan/banphong", "/home/thungan/datban", "/home/thungan/hoadon",
			"/home/thungan/doanhthu", "/home/thungan/thuchi", "/home/mathang/danhsach", "/home/mathang/dulieu",
			"/home/baocaothongke/baocaodoanhthu", "/home/baocaothongke/baocaotonkho",
			"/home/baocaothongke/baocaono", "/home/baocaothongke/baocaohieuquakinhdoanh",
			"/home/quanglychungtu/quanglynhapxuat", "/home/quanglychungtu/thuchi", "/home/quanglychungtu/dondathang",
			"/home/quanglychungtu/banle", "/home/khachhang", "/home/khachhang", "/home/nhanvien",
			"/home/caidatdulieu", "/home/hethong", "/home/thungan/banphong#", "/home/thungan/datban#",
			"/home/thungan/hoadon#", "/home/thungan/doanhthu#", "/home/thungan/thuchi#", "/home/mathang/danhsach#",
			"/home/mathang/danhmuc#", "/home/mathang/giamgia#", "/home/baocaothongke/baocaodoanhthu#",
			"/home/baocaothongke/baocaotonkho#", "/home/baocaothongke/baocaono#",
			"/home/baocaothongke/baocaohieuquakinhdoanh#", "/home/quanglychungtu/quanglynhapxuat#",
			"/home/quanglychungtu/thuchi#", "/home/quanglychungtu/dondathang#", "/home/quanglychungtu/banle#",
			"/home/khachhang", "/home/nhanvien#", "/home/caidatdulieu#",
			"/home/hethong", "/home/mathang/chitiet/{id}/" })
	public String home1(Model model, HttpServletRequest request) {
		NhanVien nv = (NhanVien) request.getSession().getAttribute("account");
		model.addAttribute("nhanVien",nv);
		return "layout/index";
	}
}
