package com.example.demo.UserRight.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.demo.UserRight.intercepter.AdminAccess;
import com.example.demo.UserRight.intercepter.CheckUser;
import com.example.demo.UserRight.intercepter.KeToanAccess;
import com.example.demo.UserRight.intercepter.ThuNganAccess;

@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// LogInterceptor áp dụng cho mọi URL.
//        registry.addInterceptor(new LogInterceptor());

		// Đường dẫn login cũ, không còn sử dụng nữa.
		// Sử dụng OldURLInterceptor để điều hướng tới một URL mới.
		List<String> ex = new ArrayList<String>();
		
//		List<String> patterns = new ArrayList<String>();
		List<String> thuNganAccess = new ArrayList<String>();
		List<String> keToanAccess = new ArrayList<String>();
//		List<String> allAccess = new ArrayList<String>();
		List<String> adminAccess = new ArrayList<String>();
		ex.add("/login");
		ex.add("/get");
		ex.add("/checklogin");
		ex.add("/assets/**");
		ex.add("/webjars/**");
		registry.addInterceptor(new CheckUser()).addPathPatterns().excludePathPatterns(ex);
		thuNganAccess.add("/ajax/thungan/*");
		thuNganAccess.add("/ajax/thungan/*");
		thuNganAccess.add("/ajax/thungan/*");
		thuNganAccess.add("/ajax/thungan/*");
		thuNganAccess.add("/ajax/thungan/*");
		registry.addInterceptor(new ThuNganAccess()).addPathPatterns(thuNganAccess);
		keToanAccess.add("/ajax/mathang/*");
		keToanAccess.add("/ajax/mathang/*");
		keToanAccess.add("/ajax/mathang/*");
		keToanAccess.add("/ajax/baocaothongke/*");
		keToanAccess.add("/ajax/baocaothongke/*");
		keToanAccess.add("/ajax/baocaothongke/*");
		keToanAccess.add("/ajax/baocaothongke/*");
		keToanAccess.add("/ajax/quanglychungtu/*");
		keToanAccess.add("/ajax/quanglychungtu/*");
		keToanAccess.add("/ajax/quanglychungtu/*");
		keToanAccess.add("/ajax/quanglychungtu/*");
		keToanAccess.add("/ajax/caidatdulieu");
		keToanAccess.add("/ajax/khachhang/*");
		keToanAccess.add("/ajax/khachhang/*");
		registry.addInterceptor(new KeToanAccess()).addPathPatterns(keToanAccess);
		adminAccess.add("/ajax/nhanvien");
		adminAccess.add("/ajax/caidatdulieu");
		adminAccess.add("/ajax/hethong");
		registry.addInterceptor(new AdminAccess()).addPathPatterns(adminAccess);

		// Interceptor này áp dụng cho các URL có dạng /admin/*
		// Loại đi trường hợp /admin/oldLogin
//        registry.addInterceptor()//
//                .addPathPatterns("/admin/*")//
//                .excludePathPatterns("/admin/oldLogin");
	}

}
