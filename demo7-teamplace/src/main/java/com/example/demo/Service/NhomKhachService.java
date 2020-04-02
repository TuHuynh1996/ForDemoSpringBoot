package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.INhomKhachService;
import com.example.demo.formClass.Sms;
import com.example.demo.model.khachhang.NhomKhach;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.NhomKhachReponsitory;

@Service
public class NhomKhachService implements INhomKhachService {
	@Autowired
	NhomKhachReponsitory nhomKhachReponsitory;
	@Autowired
	KhachHangRepository khachHangRepository;

	@Override
	public List<NhomKhach> getList() {
		if (nhomKhachReponsitory.count() == 0) {
			NhomKhach nhomKhach = new NhomKhach("1", "0", "Khách hàng bán lẻ", 1);
			nhomKhachReponsitory.save(nhomKhach);
		}
		List<NhomKhach> nk = new ArrayList<NhomKhach>();
		nhomKhachReponsitory.findAll().forEach(nk::add);
		List<NhomKhach> nk1 = new ArrayList<NhomKhach>();
		for (int i = 0; i < nk.size(); i++) {
			nk.get(i).setCon(nk);
			if (nk.get(i).getMaCha().equals("0")) {
				nk1.add(nk.get(i));
			}
		}
		return nk1;
	}

	@Override
	public Sms saveNhomKhach(NhomKhach nhomKhach) {
		Sms status;
		try {
			if (!nhomKhach.getId().equals("")) {
				status = doEditNhomKhach(nhomKhach);
			} else {
				status = doAddNhomKhach(nhomKhach);
			}
		} catch (DataAccessException e) {
			status = new Sms("-1", "DataAccessException: NhomKhachService.saveNhomKhach( " + nhomKhach + ")");
			System.out.println(status.getStatus());
		} catch (Exception e) {
			status = new Sms("-1", "Exception: NhomKhachService.saveNhomKhach( " + nhomKhach + ")");
			System.out.println(status.getStatus());
		}
		return status;
	}

	@Override
	public Sms deteleNhomKhach(NhomKhach nhomKhach) {
		Sms status;
		try {
			status = doDeleteNhomKhach(nhomKhach);
		} catch (DataAccessException e) {
			status = new Sms("-1", "DataAccessException: NhomKhachService.deteleNhomKhach( " + nhomKhach + ")");
			System.out.println(status.getStatus());
		} catch (Exception e) {
			status = new Sms("-1", "Exception: NhomKhachService.deteleNhomKhach( " + nhomKhach + ")");
			System.out.println(status.getStatus());
		}
		return status;
	}

	/////////////////////////////////////////////////////////
	private NhomKhach getTheLast() {
		NhomKhach nhomKhach = nhomKhachReponsitory.getTheLast();
		if (nhomKhach != null) {
			return nhomKhach;
		} else {
			return new NhomKhach("0", null, null, 0);
		}
	}

	private boolean existsByMaCha(String id) {
		int b = nhomKhachReponsitory.countByMaChaId(id);
		return b != 0 ? true : false;
	}

	private boolean existsByNhomKhachId(String id) {
		int a = khachHangRepository.countByNhomKhachId(id);
		return a != 0 ? true : false;
	}

	private Sms doEditNhomKhach(NhomKhach nhomKhach) {
		nhomKhachReponsitory.save(nhomKhach);
		return new Sms(nhomKhach.getId(), "Sửa thành công");
	}

	private Sms doAddNhomKhach(NhomKhach nhomKhach) {
		if (nhomKhachReponsitory.existsById(nhomKhach.getMaCha()) || nhomKhach.getMaCha().equals("0")) {
			int id = Integer.parseInt(this.getTheLast().getId());
			String aid;
			while (true) {
				id = id + 1;
				aid = String.format("%03d", id);
				if (!nhomKhachReponsitory.existsById(aid)) {
					break;
				}
			}
			nhomKhach.setId(aid);
			nhomKhachReponsitory.save(nhomKhach);
			return new Sms(aid, "Thêm thành công");
		} else {
			return new Sms("0", "Thư mục cha không tồn tại hoặc đã bị xóa từ trước");
		}
	}
	
	private Sms doDeleteNhomKhach(NhomKhach nhomKhach) {
		String id = nhomKhach.getId();
		if (!this.existsByNhomKhachId(nhomKhach.getId()) && !this.existsByMaCha(nhomKhach.getId())) {
			if (nhomKhachReponsitory.existsById(id)) {
				nhomKhachReponsitory.deleteById(id);
				return new Sms("1", "Xóa thành công!");
			} else {
				return new Sms("0", "Bộ phận này không tồn tại!");
			}
		} else {
			return new Sms("0", "Không thể xóa thư mục chứa nhân viên hoặc chứa thư mục con!");
		}
	}
}
