package com.example.demo.IService;

import java.util.List;

import com.example.demo.model.sanpham.BangGia;

public interface IBangGiaService {

	void save(BangGia bg);

	List<BangGia> getBySanPhamId(String id);

	List<BangGia> getByLoaiNhomHangId(String id);

}
