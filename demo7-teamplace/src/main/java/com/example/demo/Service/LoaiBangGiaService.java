package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.Thread.AddLbgThread;
import com.example.demo.IService.ILoaiBanGiaService;
import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.BangGia;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.example.demo.repository.BangGiaRepository;
import com.example.demo.repository.DVTKhacRepository;
import com.example.demo.repository.LoaiBangGiaRepository;
import com.example.demo.repository.SanPhamRepository;

@Service
public class LoaiBangGiaService implements ILoaiBanGiaService {
	@Autowired
	LoaiBangGiaRepository loaiBangGiaRepository;
	@Autowired
	BangGiaRepository bangGiaRepository;
	@Autowired
	SanPhamRepository sanPhamRepository;
	@Autowired
	DVTKhacRepository dvtKhacRepository;
	@Override
	public Sms edit(LoaiBangGia lbg) {
//		System.out.println(lbg.getTenBG());
		Sms sms;
		try {
			loaiBangGiaRepository.save(lbg);
			sms = new Sms("1", "Thành công");
		} catch (Exception e) {
			sms = new Sms("0", "Thất bại");
		}
		return sms;
	}

	@Override
	public Sms add(LoaiBangGia loaiBangGia) {
		Sms sms;
		try {
			if(loaiBangGiaRepository.existsById(loaiBangGia.getId())) {
				sms = new Sms("0", "Mã bảng giá đã tồn tại!");
			}else {
				loaiBangGiaRepository.save(loaiBangGia);
				Thread thread = new Thread(new AddLbgThread(bangGiaRepository, sanPhamRepository,dvtKhacRepository, loaiBangGia));
				thread.start();
				sms = new Sms("1", "Lưu thành công");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: LoaiBangGiaService add(" + loaiBangGia+ ")");
			sms = new Sms("-1","Lỗi");
		} catch(Exception e) {
			System.out.println("Exception: LoaiBangGiaService add(" + loaiBangGia+ ")");
			sms = new Sms("-1","Lỗi");
		}
		return sms;
	}
	@Override
	public Sms delete(LoaiBangGia loaiBangGia) {
		Sms sms;
		try {
			if(loaiBangGiaRepository.existsById(loaiBangGia.getId())) {
				List<BangGia> bg = new ArrayList<BangGia>();
				bangGiaRepository.getByLoaiBangGia(loaiBangGia.getId()).forEach(bg::add);
				bangGiaRepository.deleteAll(bg);
				loaiBangGiaRepository.deleteById(loaiBangGia.getId());
				sms =  new Sms("1", "Xóa thành công!");
			}else {
				sms = new Sms("0", "Bảng giá đã được xóa từ trước tồn tại!");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: LoaiBangGiaService delete(" + loaiBangGia+ ")");
			sms = new Sms("-1","Lỗi");
		} catch(Exception e) {
			System.out.println("Exception: LoaiBangGiaService delete(" + loaiBangGia+ ")");
			sms = new Sms("-1","Lỗi");
		}
		return sms;
	}
	@Override
	public List<LoaiBangGia> getAll() {
		List<LoaiBangGia> lbg = new ArrayList<LoaiBangGia>();
		loaiBangGiaRepository.findAll().forEach(lbg::add);
		return lbg;
	}

}
