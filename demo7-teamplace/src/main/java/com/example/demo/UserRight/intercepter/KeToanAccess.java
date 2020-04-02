package com.example.demo.UserRight.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.model.nhanvien.NhanVien;

public class KeToanAccess extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		NhanVien account = (NhanVien) request.getSession().getAttribute("account");
		if (account.getIsKeToan() == 1) {
			return true;
		} else {
			return false;
		}
	}

}
