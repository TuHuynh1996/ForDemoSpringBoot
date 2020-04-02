package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.INhanVienService;
import com.example.demo.formClass.DoiMatKhau;
import com.example.demo.formClass.LoginForm;
import com.example.demo.formClass.NhanVienForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.nhanvien.BoPhan;
import com.example.demo.model.nhanvien.NhanVien;
import com.example.demo.repository.BoPhanRepository;
import com.example.demo.repository.ChungTuRepository;
import com.example.demo.repository.NhanVienRepository;

@Service
public class NhanVienService implements INhanVienService {
	@Autowired
	NhanVienRepository nhanVienRepository;
	@Autowired
	BoPhanRepository boPhanRepository;
	@Autowired
	ChungTuRepository chungTuRepository;

	@Override
	public DoiMatKhau changePassword(DoiMatKhau matKhau, NhanVien nhanVien) {
		String matKhauCu = matKhau.getMauKhauCu();
		String matKhauMoi = matKhau.getMauKhauMoi();
		String xacNhan = matKhau.getXacnhan();
		if (matKhauCu.equals(nhanVien.getMatKhau()) && matKhauMoi.equals(xacNhan) && matKhauMoi.length() >= 8) {
			boolean isUppCase = false;
			boolean isLowCase = false;
			boolean isNumber = false;
			boolean isSpecial = false;
			for (int i = 0; i < matKhauMoi.length(); i++) {
				char d = matKhauMoi.charAt(i);
				String c = d + "";
				if (c.matches("[^A-Za-z0-9 ]")) {
					isSpecial = true;
				}
				if (Character.isLowerCase(d)) {
					isLowCase = true;
				}
				if (Character.isUpperCase(d)) {
					isUppCase = true;
				}
				if (Character.isDigit(d)) {
					isNumber = true;
				}
			}
			if (isUppCase == true && isLowCase == true && isNumber == true && isSpecial == true) {
				nhanVien.setMatKhau(matKhauMoi);
				nhanVienRepository.save(nhanVien);
				matKhau.setStatus("success");
			} else {
				matKhau.setStatus("fail");
			}
		} else {
			matKhau.setStatus("fail");
		}
		return matKhau;
	}
	
	@Override
	public LoginForm logIn() {
		long n = nhanVienRepository.count();
		if (n == 0) {
			NhanVien admin = new NhanVien("000", "admin", "", "", "", null, 1, 1, "admin", "");
			nhanVienRepository.save(admin);
		}
		LoginForm form = new LoginForm();
		return form;
	}

	@Override
	public List<NhanVien> listNhanVien(String boPhanId) {
		List<NhanVien> nhanVien;
		if (boPhanId.equals("0") || boPhanId == null) {
			nhanVien = new ArrayList<NhanVien>();
			nhanVienRepository.findAll().forEach(nhanVien::add);
		} else {
			nhanVien = nhanVienRepository.findNhanVienByBoPhanId(boPhanId);
		}
		return nhanVien;
	}

	@Override
	public Sms saveNhanVien(NhanVienForm nvForm) {
		Sms status = null;
		try {
			if (!nvForm.getId().equals("")) {
				status = doEditNhanVien(nvForm);
			} else {
				status = doAddNhanVien(nvForm);
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: NhanVienService saveNhanVien(" + nvForm + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: NhanVienService saveNhanVien(" + nvForm + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	@Override
	public Sms changeFolder(Sms nhanVien) {
		Sms status;
		try {
			status = doChangeFolder(nhanVien);
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: NhanVienService changeFolder(" + nhanVien + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: NhanVienService changeFolder(" + nhanVien + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	////////////////////////////////////////////////////////////////
	@Override
	public NhanVien GetNBByAccount(String name, String pass) {
		return nhanVienRepository.finNhanVienByAccount(name, pass);
	}



	@Override
	public Sms deleteNhanVien(String id) {
		Sms status;
		try {
			status = doDeleteNhanVien(id);
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: NhanVienService deleteNhanVien(" + id + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: NhanVienService deleteNhanVien(" + id + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

	private Sms doEditNhanVien(NhanVienForm nhanVienForm) {
		BoPhan boPhan;
		if (nhanVienForm.getBoPhan().equals("0")) {
			boPhan = nhanVienRepository.findById(nhanVienForm.getId()).get().getBoPhan();
		} else {
			boPhan = boPhanRepository.findById(nhanVienForm.getBoPhan()).get();
		}
		NhanVien nv = new NhanVien(nhanVienForm.getId(), nhanVienForm.getTen(), nhanVienForm.getDiaChi(),
				nhanVienForm.getDienThoai(), nhanVienForm.getMaSoThue(), boPhan, nhanVienForm.getIsKeToan(),
				nhanVienForm.getIsThuNgan(), nhanVienForm.getTenTk(), nhanVienForm.getMatKhau());
		nhanVienRepository.save(nv);
		return new Sms("1", "Chỉnh sửa thành công");
	}

	private Sms doAddNhanVien(NhanVienForm nhanVienForm) {
		String autoIncreaseNhanVienId = autoIncreaseNhanVienId();
		BoPhan boPhan = boPhanRepository.findById(nhanVienForm.getBoPhan()).get();
		NhanVien nhanVien = new NhanVien(autoIncreaseNhanVienId, nhanVienForm.getTen(), nhanVienForm.getDiaChi(),
				nhanVienForm.getDienThoai(), nhanVienForm.getMaSoThue(), boPhan, nhanVienForm.getIsKeToan(),
				nhanVienForm.getIsThuNgan(), nhanVienForm.getTenTk(), nhanVienForm.getMatKhau());
		nhanVienRepository.save(nhanVien);
		return new Sms("1", "Thêm thành công");
	}
	
	private String autoIncreaseNhanVienId() {
		int theLastId = Integer.parseInt(nhanVienRepository.findTheLastNV().getId());
		String autoIncreaseNhanVienId;
		while (true) {
			theLastId = theLastId + 1;
			autoIncreaseNhanVienId = String.format("%03d", theLastId);
			boolean isExistsId = nhanVienRepository.existsById(autoIncreaseNhanVienId);
			if (isExistsId == false) {
				break;
			}
		}
		return autoIncreaseNhanVienId;
	}

	private Sms doChangeFolder(Sms nhanVien) {
		if (nhanVienRepository.existsById(nhanVien.getId()) && boPhanRepository.existsById(nhanVien.getStatus())) {
			NhanVien nv1 = nhanVienRepository.findById(nhanVien.getId()).get();
			BoPhan bp = boPhanRepository.findById(nhanVien.getStatus()).get();
			nv1.setBoPhan(bp);
			nhanVienRepository.save(nv1);
			return new Sms("1", "Chuyển bộ phận thành công");
		} else {
			return new Sms("0", "Không tồn tại nhân viên hoặc bộ phận tương ứng");
		}
	}
	
	private Sms doDeleteNhanVien(String nhanVienId) {
		if (chungTuRepository.countByNhanVienId(nhanVienId, nhanVienId) > 0) {
			return new Sms("0", "Không thể xóa nhân viên đã xuất hiện trong chứng từ");
		} else {
			nhanVienRepository.deleteById(nhanVienId);
			return new Sms("1", "Xóa thành công");
		}
	}
}
