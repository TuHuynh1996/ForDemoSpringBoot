package com.example.demo.UserRight.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.demo.model.nhanvien.NhanVien;

public class CheckUser extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		NhanVien account = (NhanVien) request.getSession().getAttribute("account");
		if(account == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}else {
	        return true;
		}
    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
//            Object handler, ModelAndView modelAndView) throws Exception {
// 
//        // Đoạn code này sẽ không được chạy.
////        System.out.println("\n-------- OldLoginInterceptor.postHandle --- ");
//    }
// 
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
//            Object handler, Exception ex) throws Exception {
//         
//        // Đoạn code này sẽ không được chạy.
////        System.out.println("\n-------- QueryStringInterceptor.afterCompletion --- ");
//    }
}
