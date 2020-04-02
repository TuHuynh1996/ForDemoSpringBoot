package com.example.demo.Controller.Thread;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.sanpham.BangGia;
import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.example.demo.model.sanpham.SanPham;
import com.example.demo.repository.BangGiaRepository;
import com.example.demo.repository.DVTKhacRepository;
import com.example.demo.repository.SanPhamRepository;

public class AddLbgThread implements Runnable {
	private BangGiaRepository bangGiaRepository;
	private LoaiBangGia loaiBangGia;
	private SanPhamRepository sanPhamRepository;
	private DVTKhacRepository dvtKhacRepository;
	public AddLbgThread(BangGiaRepository bangGiaRepository, SanPhamRepository sanPhamRepository,DVTKhacRepository dvtKhacRepository,
			LoaiBangGia loaiBangGia) {
		super();
		this.bangGiaRepository = bangGiaRepository;
		this.loaiBangGia = loaiBangGia;
		this.sanPhamRepository = sanPhamRepository;
		this.dvtKhacRepository = dvtKhacRepository;
	}

	@Override
	public void run() {
		List<SanPham> sanPham = new ArrayList<SanPham>();
		sanPhamRepository.findAll().forEach(sanPham::add);
		List<DVTKhac> dvtKhac = new ArrayList<DVTKhac>();
		dvtKhacRepository.findAll().forEach(dvtKhac::add);
		for(DVTKhac dvt:dvtKhac) {
			bangGiaRepository.save(new BangGia(null, dvt, loaiBangGia, 0, 0));
		}
		for (int i = 0; i < sanPham.size(); i++) {
			bangGiaRepository.save(new BangGia(null, sanPham.get(i), loaiBangGia, 0, 0));
		}
		int a = (int) bangGiaRepository.count();
		System.out.println(a);
	}
}
