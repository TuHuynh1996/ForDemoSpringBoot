package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IDonViTinhService;
import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.DonViTinh;
import com.example.demo.repository.DonViTinhRepository;

@Service
public class DonViTinhService implements IDonViTinhService {
	@Autowired
	DonViTinhRepository donViTinhRepository;

	@Override
	public List<DonViTinh> getAll() {
		List<DonViTinh> dvt = new ArrayList<DonViTinh>();
		donViTinhRepository.findAll().forEach(dvt::add);
		return dvt;
	}

	@Override
	public Sms saveDVT(DonViTinh donViTinh) {
		Sms status;
		try {
			String aid;
			if (!donViTinh.getId().equals("")) {
				donViTinhRepository.save(new DonViTinh(donViTinh.getId(), donViTinh.getTen(), donViTinh.getMacDinh()));
				status = new Sms("1", "Sửa thành công");
			} else {
				int id = Integer.parseInt(this.getTheLast().getId());
				while (true) {
					id = id + 1;
					aid = String.format("%03d", id);
					if (!donViTinhRepository.existsById(aid)) {
						break;
					}
				}
				donViTinhRepository.save(new DonViTinh(aid, donViTinh.getTen(), donViTinh.getMacDinh()));
				status = new Sms("1", "Thêm thành công");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: DonViTinhService.saveDVT( "+donViTinh+")");
			status = new Sms("-1", "DataAccessException");
		} catch (Exception e) {
			System.out.println("Exception: DonViTinhService.saveDVT( "+donViTinh+")");
			status = new Sms("-1", "DataAccessException");
		}
		
		return status;
	}

	@Override
	public Sms deteleDVT(DonViTinh donViTinh) {
		Sms status;
		try {
			if (donViTinhRepository.existsById(donViTinh.getId())) {
				donViTinhRepository.deleteById(donViTinh.getId());
				status = new Sms("1", "Xóa thành công");
			} else {
				status = new Sms("1", "Xóa thành công");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: DonViTinhService.deteleDVT( "+donViTinh+")");
			status = new Sms("-1", "DataAccessException");
		} catch (Exception e) {
			System.out.println("Exception: DonViTinhService.deteleDVT( "+donViTinh+")");
			status = new Sms("-1", "DataAccessException");
		}
		return status;
	}

	@Override
	public DonViTinh getById(String id) {
		return donViTinhRepository.findById(id).get();
	}


	private DonViTinh getTheLast() {
		DonViTinh dvt = donViTinhRepository.getTheLast();
		if (dvt != null) {
			return dvt;
		} else {
			return new DonViTinh("0", null, 0);
		}
	}
}
