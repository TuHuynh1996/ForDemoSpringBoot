package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IThanhPhanService;
import com.example.demo.formClass.Sms;
import com.example.demo.formClass.ThanhPhanEditForm;
import com.example.demo.formClass.ThanhPhanForm;
import com.example.demo.model.sanpham.DonViTinh;
import com.example.demo.model.sanpham.SanPham;
import com.example.demo.model.sanpham.ThanhPhan;
import com.example.demo.repository.DonViTinhRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.repository.ThanhPhanRepository;

@Service
public class ThanhPhanService implements IThanhPhanService {
	@Autowired
	ThanhPhanRepository thanhPhanReponsitory;
	@Autowired
	SanPhamRepository sanPhamRepository;
	@Autowired
	DonViTinhRepository donViTinhRepository;

	@Override
	public Sms saveThanhPhan(ThanhPhanForm thanhPhan) {
		List<ThanhPhan> listTP = new ArrayList<ThanhPhan>();
		Sms status;
		try {
			SanPham sp = sanPhamRepository.findById(thanhPhan.getSanphamId()).get();
			DonViTinh dvt = donViTinhRepository.findById(thanhPhan.getDvtId()).get();
			for (int i = 0; i < thanhPhan.getListTen().size(); i++) {
				SanPham tenTP = sanPhamRepository.findById(thanhPhan.getListTen().get(i)).get();
				DonViTinh dvtTP = donViTinhRepository.findById(thanhPhan.getListDVT().get(i)).get();
				ThanhPhan savetp = new ThanhPhan(null, sp, dvt, tenTP, dvtTP, thanhPhan.getListSoLuong().get(i));
				listTP.add(savetp);
			}
			thanhPhanReponsitory.saveAll(listTP);
			status = new Sms("1", "Thêm thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: ThanhPhanService.saveThanhPhan(int " + thanhPhan + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: ThanhPhanService.saveThanhPhan(int " + thanhPhan + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	@Override
	public Sms deleteById(Integer id) {
		Sms status;
		try {
			if (thanhPhanReponsitory.existsById(id)) {
				thanhPhanReponsitory.deleteById(id);
				status = new Sms("1", "Xoa thanh cong");
			} else {
				status = new Sms("0", "Xoá thất bại");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: ThanhPhanService.deleteById(int " + id + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: ThanhPhanService.deleteById(int " + id + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	@Override
	public ThanhPhanEditForm edit(ThanhPhanEditForm tp) {
		try {
			if (thanhPhanReponsitory.existsById(tp.getId())) {
				SanPham sanPham = sanPhamRepository.findById(tp.getSanPhanId()).get();
				DonViTinh donViTinh = donViTinhRepository.findById(tp.getDonViTinhId()).get();
				SanPham thanhPhan = sanPhamRepository.findById(tp.getThanhPhanId()).get();
				DonViTinh dvtThanhPhan = donViTinhRepository.findById(tp.getDvtThanhPhan()).get();
				ThanhPhan saveThanhPhan = new ThanhPhan(tp.getId(), sanPham, donViTinh, thanhPhan, dvtThanhPhan,
						tp.getSoLuong());
				thanhPhanReponsitory.save(saveThanhPhan);
				tp.setThanhPhanId(thanhPhan.getTenSP());
				tp.setDvtThanhPhan(dvtThanhPhan.getTen());
			} else {
				tp = null;
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: ThanhPhanService.deleteById(int " + tp + ")");
			tp.setId(-1);
		} catch (Exception e) {
			System.out.println("Exception: ThanhPhanService.deleteById(int " + tp + ")");
			tp.setId(-1);
		}
		return tp;
	}
}
