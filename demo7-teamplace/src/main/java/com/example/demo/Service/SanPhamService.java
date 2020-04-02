package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.ISanPhamService;
import com.example.demo.formClass.MatHangForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.sanpham.BangGia;
import com.example.demo.model.sanpham.DonViTinh;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.example.demo.model.sanpham.NhomHang;
import com.example.demo.model.sanpham.SanPham;
import com.example.demo.repository.BangGiaRepository;
import com.example.demo.repository.DVTKhacRepository;
import com.example.demo.repository.DonViTinhRepository;
import com.example.demo.repository.DongChungTuRepository;
import com.example.demo.repository.LoaiBangGiaRepository;
import com.example.demo.repository.NhomHangRepository;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.repository.ThanhPhanRepository;

@Service
public class SanPhamService implements ISanPhamService {
	@Autowired
	SanPhamRepository sanPhamRepository;
	@Autowired
	DonViTinhRepository donViTinhRepository;
	@Autowired
	LoaiBangGiaRepository loaiBangGiaRepository;
	@Autowired
	NhomHangRepository nhomHangRepository;
	@Autowired
	BangGiaRepository bangGiaRepository;
	@Autowired
	DongChungTuRepository dongChungTuRepository;
	@Autowired
	DVTKhacRepository dvtKhacRepository;
	@Autowired
	ThanhPhanRepository thanhPhanRepository;

	@Override
	public List<SanPham> getList(String id) {
		List<SanPham> sp;
		if (id.equals("0")) {
			sp = new ArrayList<SanPham>();
			sanPhamRepository.findAll().forEach(sp::add);
			;
		} else {
			sp = sanPhamRepository.getByNhomHangId(id);
		}
		return sp;
	}

	@Override
	public List<SanPham> getAllThucDon(String id) {
		List<SanPham> sp;
		if (id.equals("0")) {
			sp = sanPhamRepository.getAllThucDon();
		} else {
			sp = sanPhamRepository.getAllThucDonByNhomHangId(id);
		}
		return sp;
	}

	@Override
	public Sms saveSanPham(MatHangForm sanPhamForm) {
		SanPham sp = null;
		BangGia bg;
		NhomHang nsp;
		DonViTinh donViTinh = donViTinhRepository.findById(sanPhamForm.getDvt()).get();
		Sms status = null;
		try {
			int soLuongLoaiBangGia = (int) loaiBangGiaRepository.count();
			if (!sanPhamForm.getId().equals("")) {
				String id = sanPhamForm.getId();
				if (sanPhamForm.getDonGia().size() != soLuongLoaiBangGia || !sanPhamRepository.existsById(id)) {
					status = new Sms("0", "Không khớp dữ liệu, tải lại trang trước khi thực hiện thao tác");
					return status;
				}
				if (sanPhamForm.getMaNhom().equals("0")) {
					nsp = sanPhamRepository.findById(sanPhamForm.getId()).get().getNhomHang();
				} else {
					nsp = nhomHangRepository.findById(sanPhamForm.getMaNhom()).get();
				}

				sp = new SanPham(id, nsp, sanPhamForm.getTen(), donViTinh, sanPhamForm.getSLDK(), sanPhamForm.getGTDK(),
						sanPhamForm.getIsMenu());
				List<BangGia> listbg = bangGiaRepository.getBySanPhamId(id);
				Collections.sort(listbg);
				for (int i = 0; i < soLuongLoaiBangGia; i++) {
					bg = listbg.get(i);
					bg.setDonGia(sanPhamForm.getDonGia().get(i));
					bg.setGiam(sanPhamForm.getGiam().get(i));
					bangGiaRepository.save(bg);
				}
				sanPhamRepository.save(sp);
				status = new Sms(sanPhamForm.getMaNhom(), "Chỉnh sửa thành công");
			} else {
				if (sanPhamForm.getDonGia().size() != soLuongLoaiBangGia) {
					status = new Sms("0", "Không khớp dữ liệu, tải lại trang trước khi thực hiện thao tác");
					return status;
				}
				SanPham spid = this.getTheLast();
				int id = Integer.parseInt(spid.getId());
				String aid;
				while (true) {
					id = id + 1;
					aid = String.format("%03d", id);
					if (!sanPhamRepository.existsById(aid)) {
						break;
					}
				}
				nsp = nhomHangRepository.findById(sanPhamForm.getMaNhom()).get();
				sp = new SanPham(aid, nsp, sanPhamForm.getTen(), donViTinh, sanPhamForm.getSLDK(),
						sanPhamForm.getGTDK(), sanPhamForm.getIsMenu());
				sanPhamRepository.save(sp);

				List<LoaiBangGia> lbg = new ArrayList<LoaiBangGia>();
				loaiBangGiaRepository.findAll().forEach(lbg::add);
				Collections.sort(lbg);
				for (int i = 0; i < lbg.size(); i++) {
					bangGiaRepository.save(new BangGia(null, sp, lbg.get(i), sanPhamForm.getDonGia().get(i),
							sanPhamForm.getGiam().get(i)));

				}
				status = new Sms(sanPhamForm.getMaNhom(), "Thêm thành công");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: SanPhamService.saveSanPham(int " + sanPhamForm + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: SanPhamService.saveSanPham(int " + sanPhamForm + ")");
			status = new Sms("-1", "Lỗi");
		}

		return status;
	}

	@Override
	public Sms changeFolder(Sms nv) {
		Sms status;
		try {
			if (sanPhamRepository.existsById(nv.getId()) && nhomHangRepository.existsById(nv.getStatus())) {
				SanPham sp = sanPhamRepository.findById(nv.getId()).get();
				NhomHang nk = nhomHangRepository.findById(nv.getStatus()).get();
				sp.setNhomHang(nk);
				sanPhamRepository.save(sp);
				status = new Sms("1", "Chuyển bộ phận thành công");
			} else {
				status = new Sms("0", "404-Không tồn tại mặt hàng hoặc thư mục tương ứng");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: SanPhamService.changeFolder(int " + nv + ")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: SanPhamService.changeFolder(int " + nv + ")");
			status = new Sms("-1", "Lỗi");
		}

		return status;
	}

	///////////////////////////////////////////////////////////////////////////
	@Override
	public SanPham getbyId(String id) {
		return sanPhamRepository.findById(id).get();
	}

	@Override
	public List<SanPham> getAllThanhPhan() {
		List<NhomHang> nh = nhomHangRepository.getByLoaiNhom(1);
		List<SanPham> sp = new ArrayList<SanPham>();
		for (NhomHang nhomHang : nh) {
			sanPhamRepository.getByNhomHangId(nhomHang.getId()).forEach(sp::add);
		}
		return sp;
	}
	
	@Override
	public Sms deleteSanPham(String id) {
		Sms status;
		try {
			if(dongChungTuRepository.countBySanPhamId(id) > 0) {
				return new Sms("0", "Không thể xóa sản phẩm đã tồn tại chứng từ.");
			}else {
				thanhPhanRepository.deleteAll(thanhPhanRepository.findBySanPhamId(id));
				bangGiaRepository.deleteAll(bangGiaRepository.getBySanPhamId(id));
				dvtKhacRepository.deleteAll(dvtKhacRepository.findBySanPhamId(id));
				sanPhamRepository.deleteById(id);
				return new Sms("1", "Xóa thành công.");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: SanPhamService.deleteSanPham(String " + id + ")");
			status = new Sms("-1", "Lỗi");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception: SanPhamService.deleteSanPham(String " + id + ")");
			status = new Sms("-1", "Lỗi");
		}
		return status;
	}

//////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////
	private SanPham getTheLast() {
		SanPham sp = sanPhamRepository.getTheLast();
		if (sp != null) {
			return sp;
		} else {
			return new SanPham("0", null, null, null, 0, 0, 0);
		}
	}



}
