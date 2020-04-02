package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.INhomHangService;
import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.NhomHang;
import com.example.demo.repository.NhomHangRepository;
import com.example.demo.repository.SanPhamRepository;

@Service
public class NhomHangService implements INhomHangService {
	@Autowired
	NhomHangRepository nhomHangRepository;
	@Autowired
	SanPhamRepository sanPhamRepository;

	@Override
	public List<NhomHang> getList() {
		List<NhomHang> nh = new ArrayList<NhomHang>();
		nhomHangRepository.findAll().forEach(nh::add);
		List<NhomHang> nh1 = new ArrayList<NhomHang>();
		for (int i = 0; i < nh.size(); i++) {
			nh.get(i).setCon(nh);
			if (nh.get(i).getMaCha().equals("0")) {
				nh1.add(nh.get(i));
			}
		}
		return nh1;
	}

	@Override
	public Sms saveNhomHang(NhomHang nhomHang) {
		System.out.println(nhomHang.getTen());
		Sms status;
		try {
			if (!nhomHang.getId().equals("")) {
				status = doEditNhomHang(nhomHang);
			} else {
				status = doAddNhomHang(nhomHang);
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: NhomHangService.add( " + nhomHang + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: NhomHangService.add( " + nhomHang + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	@Override
	public Sms deteleNhomHang(NhomHang nhomHang) {
		Sms status;
		try {
			status = doDeleteNhomHang(nhomHang);
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: NhomHangService.deteleNhomHang( " + nhomHang + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: NhomHangService.deteleNhomHang( " + nhomHang + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	public boolean existsByNhomHangId(String id) {
		int n = sanPhamRepository.countByNhomHangId(id);
		return n != 0 ? true : false;
	}

	private NhomHang getTheLast() {
		NhomHang nh = nhomHangRepository.getTheLast();
		if (nh != null) {
			return nh;
		} else {
			return new NhomHang("0", null, null, 0);
		}
	}

	private boolean existsByMaCha(String macha) {
		int n = nhomHangRepository.countByMacha(macha);
		return n != 0 ? true : false;
	}

	private Sms doDeleteNhomHang(NhomHang nhomHang) throws Exception {
		String id = nhomHang.getId();
		if (!this.existsByNhomHangId(id) && !this.existsByMaCha(id)) {
			if (nhomHangRepository.existsById(id)) {
				nhomHangRepository.deleteById(id);
				return new Sms("1", "Xóa thành công!");
			} else {
				return new Sms("0", "Bộ phận này không tồn tại!");
			}
		} else {
			return new Sms("0", "Không thể xóa thư mục chứa nhân viên hoặc chứa thư mục con!");
		}
	}

	private Sms doEditNhomHang(NhomHang nhomHang) {
		nhomHangRepository.save(nhomHang);
		return new Sms(nhomHang.getId(), "Sửa thành công");
	}

	private Sms doAddNhomHang(NhomHang nhomHang) {
		if (nhomHangRepository.existsById(nhomHang.getMaCha()) || nhomHang.getMaCha().equals("0")) {
			int id = Integer.parseInt(this.getTheLast().getId());
			String aid;
			while (true) {
				id = id + 1;
				aid = String.format("%03d", id);
				if (!nhomHangRepository.existsById(aid)) {
					break;
				}
			}
			nhomHang.setId(aid);
			nhomHangRepository.save(nhomHang);
			return new Sms(id + "", "Thêm thành công");
		} else {
			return new Sms("0", "Thư mục cha không tồn tại hoặc đã bị xóa từ trước");
		}
	}
}
