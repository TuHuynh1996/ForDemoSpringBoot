package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IDVTKhacService;
import com.example.demo.formClass.AddDonViTinhKhacForm;
import com.example.demo.formClass.DonViTinhKhacEditForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.BangGia;
import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.DonViTinh;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.example.demo.model.sanpham.SanPham;
import com.example.demo.repository.BangGiaRepository;
import com.example.demo.repository.DVTKhacRepository;
import com.example.demo.repository.DonViTinhRepository;
import com.example.demo.repository.LoaiBangGiaRepository;
import com.example.demo.repository.SanPhamRepository;

@Service
public class DVTKhacService implements IDVTKhacService {
	@Autowired
	DVTKhacRepository dvtKhacRepository;
	@Autowired
	SanPhamRepository sanPhamRepository;
	@Autowired
	DonViTinhRepository donViTinhRepository;
	@Autowired
	BangGiaRepository bangGiaRepository;
	@Autowired
	LoaiBangGiaRepository loaiBangGiaRepository;

	@Override
	public Sms add(AddDonViTinhKhacForm donViTinhForm) {
		Sms status;
		try {
			SanPham sanPham;
			if (sanPhamRepository.existsById(donViTinhForm.getSanPhamId())) {
				sanPham = sanPhamRepository.findById(donViTinhForm.getSanPhamId()).get();
			} else {
				return status = new Sms("0", "Lỗi! Không tìm thấy đơn vị tính");
			}
			long donGia, giam;
			int size = donViTinhForm.getMaDonViTinh().size();
			int sizeBangGia = donViTinhForm.getDonGia().get(0).size();
			List<LoaiBangGia> loaiBangGia = new ArrayList<LoaiBangGia>();
			loaiBangGiaRepository.findAll().forEach(loaiBangGia::add);
			Collections.sort(loaiBangGia);
			for (int i = 0; i < size; i++) {
				DonViTinh donViTinh = donViTinhRepository.findById(donViTinhForm.getMaDonViTinh().get(i)).get();
				double quiDoi = donViTinhForm.getQuiDoi().get(i);
				int menu = donViTinhForm.getIsMenu().get(i);
				DVTKhac donViTinhKhacSaved = dvtKhacRepository.save(new DVTKhac(null, sanPham, donViTinh, quiDoi, menu));
				for (int j = 0; j < sizeBangGia; j++) {
					donGia = donViTinhForm.getDonGia().get(i).get(j);
					giam = donViTinhForm.getGiam().get(i).get(j);
					bangGiaRepository.save( new BangGia(null, donViTinhKhacSaved, loaiBangGia.get(j), donGia, giam));
				}
			}
			status = new Sms("1", "Thêm thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: DVTKhacService.add(int "+donViTinhForm.getSanPhamId()+")");
			status = new Sms("-1", "DataAccessException");
		} catch (Exception e) {
			System.out.println("Exception: DVTKhacService.add(int "+donViTinhForm.getSanPhamId()+")");
			status = new Sms("-1", "DataAccessException");
		}
		
		return status;
	}

	@Override
	public Sms delete(Integer id) {
		Sms status;
		try {
			bangGiaRepository.deleteAll(bangGiaRepository.getByDVTKhacId(id));
			dvtKhacRepository.deleteById(id);
			status = new Sms("1", "xóa thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: DVTKhacService.delete( " + id + ")");
			status = new Sms("-1", "DataAccessException");
		} catch (Exception e) {
			System.out.println("Exception: DVTKhacService.delete( " + id + ")");
			status = new Sms("-1", "DataAccessException");
		}
		return status;
	}

	@Override
	public Sms edit(DonViTinhKhacEditForm donViTinhKhacEditForm) {
		Sms status;
		try {
			List<BangGia> bangGia = new ArrayList<BangGia>(dvtKhacRepository.findById(donViTinhKhacEditForm.getId()).get().getBangGia());
			SanPham sanPham = sanPhamRepository.findById(donViTinhKhacEditForm.getSanPhamId()).get();
			DonViTinh donViTinh = donViTinhRepository.findById(donViTinhKhacEditForm.getDonViTinh()).get();
			dvtKhacRepository.save(new DVTKhac(donViTinhKhacEditForm.getId(), sanPham, donViTinh, donViTinhKhacEditForm.getQuiDoi(), donViTinhKhacEditForm.getIsMenu()));
			for(int i = 0; i < bangGia.size(); i++) {
				bangGia.get(i).setDonGia(donViTinhKhacEditForm.getDonGia().get(i));
				bangGia.get(i).setGiam(donViTinhKhacEditForm.getGiam().get(i));
			}
			bangGiaRepository.saveAll(bangGia);
			status = new Sms("1", "Chỉnh sửa thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: DVTKhacService.edit(int " + donViTinhKhacEditForm.getId() + ")");
			status = new Sms("-1", "DataAccessException");
		} catch (Exception e) {
			System.out.println("Exception: DVTKhacService.edit(int " + donViTinhKhacEditForm.getId() + ")");
			status = new Sms("-1", "DataAccessException");
		}
		return status;
	}
}
