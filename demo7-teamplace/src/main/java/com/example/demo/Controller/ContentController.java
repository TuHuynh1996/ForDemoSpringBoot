package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.IService.IBoPhanService;

@Controller
public class ContentController {

	@Autowired
	IBoPhanService boPhanService;

	@RequestMapping(value = "/ajax/demo1")
	public String demo1() {
		return null;
	}

	@RequestMapping(value = "/ajax/thungan/banphong")
	public String thuNgan1() {
		return "content/thungan/banphong/banphong";
	}

	@RequestMapping(value = "/ajax/thungan/datban")
	public String thuNgan2() {
		return "content/thungan/datban/datban";
	}

	@RequestMapping(value = "/ajax/thungan/hoadon")
	public String thuNgan3() {
		return "content/thungan/hoadon";
	}

	@RequestMapping(value = "/ajax/thungan/doanhthu")
	public String thuNgan4() {
		return "content/thungan/doanhthu";
	}

	@RequestMapping(value = "/ajax/thungan/thuchi")
	public String thuNgan5() {
		return "content/thungan/thuchi";
	}

//	@RequestMapping(value = "/ajax/mathang")
//	public String mathang1() {
//		return "content/mathang/mathang";
//	}

	@RequestMapping(value = "/ajax/mathang/danhsach")
	public String matHang2() {
		return "content/mathang/mathang";
	}

	@RequestMapping(value = "/ajax/mathang/dulieu")
	public String matHang3() {
		return "content/mathang/dulieu";
	}

	@RequestMapping(value = "/ajax/baocaothongke/baocaodoanhthu")
	public String baoCaoThongKe1() {
		return "content/baocaothongke/baocaodoanhthu";
	}

	@RequestMapping(value = "/ajax/baocaothongke/baocaotonkho")
	public String baoCaoThongKe2() {
		return "content/baocaothongke/baocaotonkho";
	}

	@RequestMapping(value = "/ajax/baocaothongke/baocaono")
	public String baoCaoThongKe3() {
		return "content/baocaothongke/baocaono";
	}

	@RequestMapping(value = "/ajax/baocaothongke/baocaohieuquakinhdoanh")
	public String baoCaoThongKe4() {
		return "content/baocaothongke/baocaohieuquakinhdoanh";
	}

	@RequestMapping(value = "/ajax/quanglychungtu/quanglynhapxuat")
	public String quangLyChungTu1() {
		return "content/quanglychungtu/quanglynhapxuat";
	}

	@RequestMapping(value = "/ajax/quanglychungtu/thuchi")
	public String quangLyChungTu2() {
		return "content/quanglychungtu/thuchi";
	}

	@RequestMapping(value = "/ajax/quanglychungtu/dondathang")
	public String quangLyChungTu3() {
		return "content/quanglychungtu/dondathang";
	}

	@RequestMapping(value = "/ajax/quanglychungtu/banle")
	public String quangLyChungTu4() {
		return "content/quanglychungtu/banle/banle";
	}

	@RequestMapping(value = "/ajax/khachhang")
	public String khachHang1() {
		return "content/khachhang/khachhang";
	}

	@RequestMapping(value = "/ajax/nhanvien")
	public String nhanVien() {
		return "content/nhanvien/nhanvien";
	}

	@RequestMapping(value = "/ajax/caidatdulieu")
	public String caiDatDuLieu() {
		return "content/caidatdulieu";
	}

	@RequestMapping(value = "/ajax/hethong")
	public String heThong() {
		return "content/hethong";
	}

	@RequestMapping(value = "/ajax/error")
	public String error() {
		return "other/error";
	}
}
