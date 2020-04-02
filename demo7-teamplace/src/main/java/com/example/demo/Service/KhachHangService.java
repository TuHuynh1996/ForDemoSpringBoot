package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IKhachHangService;
import com.example.demo.formClass.KhachHangForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.khachhang.KhachHang;
import com.example.demo.model.khachhang.NhomKhach;
import com.example.demo.repository.ChungTuRepository;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.NhomKhachReponsitory;

@Service
public class KhachHangService implements IKhachHangService {
	@Autowired
	KhachHangRepository khachHangRepository;
	@Autowired
	NhomKhachReponsitory nhomKhachReponsitory;
	@Autowired
	ChungTuRepository chungTuRepository;

	@Override
	public List<KhachHang> getListKhach(String id) {
		System.out.println(id);
		List<KhachHang> kh;
		if (id.equals("0")) {
			kh = new ArrayList<KhachHang>();
			khachHangRepository.findAll().forEach(kh::add);
		} else {
			System.out.println(id);
			kh = khachHangRepository.findByNhomKhachId(id);
		}
		return kh;
	}

	@Override
	public Sms saveKhach(KhachHangForm khachHangForm) {
		Sms status;
		try {
			if (!khachHangForm.getId().equals("")) {
				NhomKhach nhomKhach;
				if (khachHangForm.getNhomKhach().equals("0")) {
					nhomKhach = khachHangRepository.findById(khachHangForm.getId()).get().getNhomKhach();
				} else {
					nhomKhach = nhomKhachReponsitory.findById(khachHangForm.getNhomKhach()).get();
				}
				KhachHang khachHang = new KhachHang(khachHangForm.getId(), khachHangForm.getTen(), khachHangForm.getDiaChi(),
						khachHangForm.getDienThoai(), khachHangForm.getMaSoThue(), nhomKhach, khachHangForm.getThuDK(),
						khachHangForm.getTraDK());
				khachHangRepository.save(khachHang);
				status = new Sms("1", "Chỉnh sửa thành công");
			} else {
				int id = Integer.parseInt(this.getTheLast().getId());
				String aid;
				while (true) {
					id = id + 1;
					aid = String.format("%03d", id);
					boolean idKhachHang = khachHangRepository.existsById(aid);
					if (idKhachHang == false) {
						break;
					}
				}
				NhomKhach nhomKhach = nhomKhachReponsitory.findById(khachHangForm.getNhomKhach()).get();
				KhachHang khachHang = new KhachHang(aid, khachHangForm.getTen(), khachHangForm.getDiaChi(), khachHangForm.getDienThoai(),
						khachHangForm.getMaSoThue(), nhomKhach, khachHangForm.getThuDK(), khachHangForm.getTraDK());
				khachHangRepository.save(khachHang);
				status = new Sms("1", "Thêm thành công");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: KhachHangService.saveKhach(int "+khachHangForm+")");
			status = new Sms("-1", "Lỗi");
		}catch (Exception e) {
			System.out.println("Exception: KhachHangService.saveKhach(int "+khachHangForm+")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	@Override
	public Sms changeFolder(Sms nhanVienId) {
		Sms status;
		try {
			if (khachHangRepository.existsById(nhanVienId.getId()) && nhomKhachReponsitory.existsById(nhanVienId.getStatus())) {
				KhachHang kh = khachHangRepository.findById(nhanVienId.getId()).get();
				NhomKhach nk = nhomKhachReponsitory.findById(nhanVienId.getStatus()).get();
				kh.setNhomKhach(nk);
				khachHangRepository.save(kh);
				status = new Sms("1", "Chuyển bộ phận thành công");
			} else {
				status = new Sms("0", "404-Không tồn tại nhân viên hoặc bộ phận tương ứng");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: KhachHangService.changeFolder( "+nhanVienId+")");
			status = new Sms("-1", "Lỗi");
		}catch (Exception e) {
			System.out.println("Exception: KhachHangService.changeFolder( "+nhanVienId+")");
			status = new Sms("-1", "Lỗi");
		}
		
		return status;
	}

///////////////////////////////////////
	private KhachHang getTheLast() {
		KhachHang kh = khachHangRepository.findTheLastKh();
		if (kh != null) {
			return kh;
		} else {
			return new KhachHang("0", null, null, null, null, null, 0, 0);
		}
	}

	@Override
	public Sms deleteBoPhan(String id) {
		Sms status;
		try {
			if(chungTuRepository.countByKhachHangId(id) > 0) {
				return new Sms("0", "Không thể xóa khách hàng đã xuất hiện trong chứng từ");
			}else {
				khachHangRepository.deleteById(id);
				return new Sms("1", "Xóa thành công");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("DataAccessException: KhachHangService deleteKhachHang(" + id+ ")");
			status = new Sms("-1","Lỗi");
		} catch(Exception e) {
			System.out.println("Exception: KhachHangService deleteKhachHang(" + id+ ")");
			status = new Sms("-1","Lỗi");
		}
		return status;
	}
}
