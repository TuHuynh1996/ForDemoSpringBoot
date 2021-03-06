package com.example.demo.UserRight.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.model.nhanvien.NhanVien;

public class AdminAccess extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		NhanVien account = (NhanVien) request.getSession().getAttribute("account");
		if (account.getTen().equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

}
