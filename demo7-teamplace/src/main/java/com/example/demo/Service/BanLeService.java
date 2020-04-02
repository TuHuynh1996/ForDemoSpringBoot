package com.example.demo.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IBanLeService;
import com.example.demo.formClass.Sms;
import com.example.demo.formClass.DatBanForm.BanLeForm;
import com.example.demo.formClass.DatBanForm.ThucDonForm;
import com.example.demo.model.banphong.BanPhong;
import com.example.demo.model.chungtu.BanLe;
import com.example.demo.model.chungtu.DonDatHang;
import com.example.demo.model.chungtu.DongChungTu;
import com.example.demo.model.khachhang.KhachHang;
import com.example.demo.model.nhanvien.NhanVien;
import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.SanPham;
import com.example.demo.repository.BanLeRepository;
import com.example.demo.repository.BanPhongRepository;
import com.example.demo.repository.DVTKhacRepository;
import com.example.demo.repository.DonDatHangRepository;
import com.example.demo.repository.DongChungTuRepository;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.SanPhamRepository;

@Service
public class BanLeService implements IBanLeService {
	@Autowired
	BanLeRepository banLeRepository;
	@Autowired
	BanPhongRepository banPhongRepository;
	@Autowired
	KhachHangRepository khachHangRepository;
	@Autowired
	NhanVienRepository nhanVienRepository;
	@Autowired
	SanPhamRepository sanPhamRepository;
	@Autowired
	DVTKhacRepository dvtKhacRepository;
	@Autowired
	DongChungTuRepository dongChungTuRepository;
	@Autowired
	DonDatHangRepository donDatHangRepository;

	@Override
	public Sms save(BanLeForm banLeForm) {
		Sms sms = new Sms("0", "Số lượng thực đơn không được âm");
		List<ThucDonForm> thucDon = new ArrayList<ThucDonForm>(banLeForm.getThucDon());
		List<DongChungTu> dongChungTu = new ArrayList<DongChungTu>();
		BanLe banLe = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			BanPhong banPhong = banPhongRepository.findById(banLeForm.getSoBan()).get();
			KhachHang khachHang = banLeForm.getKhachHang().equals("") ? null
					: khachHangRepository.findById(banLeForm.getKhachHang()).get();
			NhanVien thuNgan = nhanVienRepository.findById(banLeForm.getThuNgan()).get();
			NhanVien nhanVien = banLeForm.getPhucVu().equals("") ? null
					: nhanVienRepository.findById(banLeForm.getPhucVu()).get();
			String loaiBangGiaId = banPhong.getMaBG().getId();
			long soTien = 0;
			// Xử lý thực đơn
			for (ThucDonForm thucDonForm : thucDon) {
				if (thucDonForm.getSoLuong() < 0) {
					return new Sms("0", "Số lượng thực đơn không được âm");
				}
				String[] id = thucDonForm.getId().split("-");
				if (id[0].equals("dvtkhac")) {
					DVTKhac donViTinhKhac = dvtKhacRepository.findById(Integer.parseInt(id[1])).get();
					donViTinhKhac.setBangGiaSelected(loaiBangGiaId);
					long donGia = (long) (donViTinhKhac.getDonGiaByLoaiBangGia(loaiBangGiaId)
							* thucDonForm.getSoLuong());

					dongChungTu.add(new DongChungTu(null, null, donViTinhKhac, (double) thucDonForm.getSoLuong(),
							donViTinhKhac.getBangGiaSelected().getDonGia(), "", "", 0,
							donViTinhKhac.getBangGiaSelected().getGiam(), thucDonForm.getGhiChu()));
					soTien = soTien + donGia;
				} else {
					SanPham sanPham = sanPhamRepository.findById(thucDonForm.getId()).get();
					sanPham.setBangGiaSelected(loaiBangGiaId);
					long donGia = (long) (sanPham.getDonGiaByLoaiBangGia(loaiBangGiaId) * thucDonForm.getSoLuong());
					dongChungTu.add(new DongChungTu(null, null, sanPham, (double) thucDonForm.getSoLuong(),
							sanPham.getBangGiaSelected().getDonGia(), "", "", 0, sanPham.getBangGiaSelected().getGiam(),
							thucDonForm.getGhiChu()));
					soTien = soTien + donGia;
				}
			}
			soTien = soTien * (1 - banLeForm.getGiam() / 100) * (1 + banLeForm.getThue() / 100)
					* (1 + banLeForm.getPhiPV() / 100);
			Date ngayCT = format.parse(banLeForm.getGioVao());
			// Xử lý chứng từ bán lẻ
			if (banLeForm.getSoPhieu().equals("")) {
				if (this.isFree(banLeForm.getSoBan())) {
					banLe = new BanLe(getNewID(), ngayCT, banLeForm.getGhiChu(), 0, banPhong, khachHang,
							banLeForm.getSoLuongKhach(), thuNgan, nhanVien, banLeForm.getGiam(), banLeForm.getThue(),
							banLeForm.getPhiPV(), soTien, banLeForm.getTraTruoc(), banLeForm.getConNo());
				} else {
					return new Sms("0", "Bàn đang được sử dụng");
				}
			} else {
				Set<DongChungTu> deleteDongChungTu = banLeRepository.findById(banLeForm.getSoPhieu()).get()
						.getDongChungTu();
				dongChungTuRepository.deleteAll(deleteDongChungTu);
				if (banLeRepository.existsById(banLeForm.getSoPhieu())) {
					BanLe checkBanLe = banLeRepository.findById(banLeForm.getSoPhieu()).get();
					if (checkBanLe.getTrangThai() == 1) {
						return new Sms("0", "Bạn không thể chỉnh sửa chứng từ");
					} else {
						banLe = new BanLe(banLeForm.getSoPhieu(), ngayCT, banLeForm.getGhiChu(), 0, banPhong, khachHang,
								banLeForm.getSoLuongKhach(), thuNgan, nhanVien, banLeForm.getGiam(),
								banLeForm.getThue(), banLeForm.getPhiPV(), soTien, banLeForm.getTraTruoc(),
								banLeForm.getConNo());
					}
				} else {
					return new Sms("0", "Không tồm tại");
				}
			}
			// Tiến hành lưu
			BanLe banLeSaved = banLeRepository.save(banLe);
			for (DongChungTu value : dongChungTu) {
				value.setChungTu(banLeSaved);
			}
			dongChungTuRepository.saveAll(dongChungTu);
			sms = new Sms("1", "Thêm thành công");
		} catch (DataAccessException e) {
			sms = new Sms("-1", "DataAccessException : BanLeService save(" + banLeForm + ")");
			System.out.println(sms.getStatus());
			e.printStackTrace();
		} catch (Exception e) {
			sms = new Sms("-1", "Exception : BanLeService save(" + banLeForm + ")");
			System.out.println(sms.getStatus());
			e.printStackTrace();
		}
		return sms;
	}

	@Override
	public Sms delete(Sms sms) {
		Sms sms1;
		try {
			BanLe banLe = banLeRepository.findById(sms.getId()).get();
			if (banLe.getTrangThai() == 0) {
				dongChungTuRepository.deleteAll(banLe.getDongChungTu());
				banLeRepository.delete(banLe);
				sms1 = new Sms("1", "Xóa thành công");
			} else {
				return new Sms("0", "Lỗi! Không thể xóa");
			}
		} catch (DataAccessException e) {
			sms1 = new Sms("-1", "DataAccessException : BanLeService delete(" + sms + ")");
			System.out.println(sms.getStatus());
			e.printStackTrace();
		} catch (Exception e) {
			sms1 = new Sms("-1", "Exception : BanLeService delete(" + sms + ")");
			System.out.println(sms.getStatus());
			e.printStackTrace();
		}
		return sms1;
	}

	@Override
	public List<SanPham> getThucDon(String id) {
		List<SanPham> thucDonList = sanPhamRepository.getAllThucDon();
		for (SanPham thucDon : thucDonList) {
			thucDon.setBangGiaSelected(id);
			for (DVTKhac value : thucDon.getDvtKhac()) {
				value.setBangGiaSelected(id);
			}
		}
		return thucDonList;
	}

	@Override
	public Sms change(Sms sms) {
		Sms sms1;
		try {
			BanLe banLe = banLeRepository.findById(sms.getId()).get();
			if (!this.isFree(sms.getStatus())) {
				return new Sms("0", "Bàn đang có người!");
			}
			if (banLe.getTrangThai() != 0) {
				return new Sms("0", "Không thể thay đổi chứng từ");
			} else {
				long tongTien = 0;
				BanPhong banPhong = banPhongRepository.findById(sms.getStatus()).get();
				for (DongChungTu dongChungTu : banLe.getDongChungTu()) {
					
					tongTien = tongTien +dongChungTu.resetTien(banPhong.getMaBG().getId())*(long)dongChungTu.getSoLuong();
				}
				banLe.setBanPhong(banPhong);
				banLe.setSoTien(tongTien);
				banLeRepository.save(banLe);
				sms1 = new Sms("1", "Thành công");
			}
		} catch (DataAccessException e) {
			sms1 = new Sms("-1", "DataAccessException : BanLeService change(" + sms + ")");
			System.out.println(sms.getStatus());
		} catch (Exception e) {
			sms1 = new Sms("-1", "Exception : BanLeService change(" + sms + ")");
			System.out.println(sms.getStatus());
		}
		return sms1;
	}

	@Override
	public BanLe getById(String id) {
		try {
			BanLe banLe = banLeRepository.findById(id).get();
			if (banLe.getTrangThai() != 0) {
				return null;
			} else {
				return banLeRepository.findById(id).get();
			}
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public Sms thanhtoan(Sms sms) {
		Sms sms1;
		try {
			BanLe banLe = banLeRepository.findById(sms.getId()).get();
			if (banLe.getTrangThai() != 0) {
				return new Sms("0", "Đã xảy ra lỗi");
			} else {
				banLe.setTrangThai(1);
				banLeRepository.save(banLe);
				sms1 = new Sms("1", "Thành công");
			}
		} catch (DataAccessException e) {
			sms1 = new Sms("-1", "DataAccessException : BanLeService change(" + sms + ")");
			System.out.println(sms1.getStatus());
		}
		return sms1;
	}

	@Override
	public Sms graft(Sms sms) {
		System.out.println(sms.getId());
		System.out.println(sms.getStatus());
		Sms sms1;
		try {
			BanLe banLe1 = banLeRepository.findById(sms.getId()).get();
			BanLe banLe2 = banLeRepository.findById(sms.getStatus()).get();
			if (banLe1.getTrangThai() != 0 || banLe2.getTrangThai() != 0) {
				return new Sms("0", "Đã xảy ra lỗi");
			} else {
				Set<DongChungTu> dongChungTuList = banLe2.getDongChungTu();
				long tongTien = 0;
				for (DongChungTu dongChungTu : banLe1.getDongChungTu()) {
					boolean flag = false;
					for (DongChungTu dongChungTu2 : dongChungTuList) {
						if (dongChungTu.getName().equals(dongChungTu2.getName())) {
							dongChungTu2.setSoLuong(dongChungTu.getSoLuong() + dongChungTu2.getSoLuong());
							flag = true;
							break;
						}
					}
					if (flag == false) {
						dongChungTu.setChungTu(banLe2);
						dongChungTuList.add(dongChungTu);
					}
				}
				dongChungTuRepository.deleteAll(banLe1.getDongChungTu());
				dongChungTuRepository.saveAll(dongChungTuList);
				for (DongChungTu dongChungTu : dongChungTuList) {
					tongTien = tongTien + dongChungTu.resetTien(banLe2.getBanPhong().getMaBG().getId())*(long)dongChungTu.getSoLuong();
				}
				banLe2.setSoTien(tongTien);
				banLe2.setSoKhach(banLe1.getSoKhach() + banLe2.getSoKhach());
				banLe2.setTraTruoc(banLe2.getTraTruoc() + banLe1.getTraTruoc());
				banLe2.setDongChungTu(null);
				banLeRepository.save(banLe2);
				banLeRepository.delete(banLe1);
				sms1 = new Sms("1", "Thành công");
			}
		} catch (DataAccessException e) {
			sms1 = new Sms("-1", "DataAccessException : BanLeService graft(" + sms + ")");
			System.out.println(sms1.getStatus());
			e.printStackTrace();

		} catch (Exception e) {
			sms1 = new Sms("-1", "Exception : BanLeService graft(" + sms + ")");
			System.out.println(sms.getStatus());
			e.printStackTrace();
		}

		return sms1;
	}

	@Override
	public Sms thanhToanDatHang(Sms sms) {
		Sms sms1;
		try {
			Date currentDate = new Date();
			DonDatHang donDatHang = donDatHangRepository.findById(sms.getId()).get();
			NhanVien thuNgan = nhanVienRepository.findById(sms.getStatus()).get();
			if (donDatHang.getTrangThai() != 0) {
				return new Sms("0", "Đã xảy ra lỗi!");
			} else {
				BanLe banLe = new BanLe(this.getNewID(), currentDate, donDatHang.getNoiDung(), 1, null,
						donDatHang.getKhachHang(), donDatHang.getSoKhach(), thuNgan, null,(long) donDatHang.getGiam(), 0, 0,
						donDatHang.getSoTien(), donDatHang.getTraTruoc(), 0);
				List<DongChungTu> thucDon = new ArrayList<DongChungTu>(donDatHang.getDongChungTu());
				banLeRepository.save(banLe);
				for(DongChungTu dongChungTu:thucDon) {
					dongChungTu.setChungTu(banLe);
				}
				dongChungTuRepository.saveAll(thucDon);
				donDatHang.setTrangThai(1);
				donDatHangRepository.save(donDatHang);
				sms1 = new Sms("1", "Thành công");
			}
		} catch (DataAccessException e) {
			sms1 = new Sms("-1", "DataAccessException : DonDatHangService delete(" + sms + ")");
			System.out.println(sms.getStatus());
		} catch (Exception e) {
			sms1 = new Sms("-1", "Exception : DonDatHangService delete(" + sms + ")");
			System.out.println(sms.getStatus());
		}
		return sms1;
	}

	@Override
	public List<BanPhong> getBanTrong() {
		List<BanPhong> banPhong = banPhongRepository.getAllBanPhongMenu();
		List<BanPhong> banPhongFree = new ArrayList<BanPhong>();
		for(BanPhong value:banPhong) {
			if(this.isFree(value.getId())) {
				banPhongFree.add(value);
			}
		}
		return banPhongFree;
	}
	
	@Override
	public Sms vaoBan(Sms sms, NhanVien thuNgan) {
		System.out.println(sms);
		Sms sms1;
		try {
			Date currentDate = new Date();
			DonDatHang donDatHang = donDatHangRepository.findById(sms.getId()).get();
			BanPhong banPhong = banPhongRepository.findById(sms.getStatus()).get();
			if (donDatHang.getTrangThai() != 0 || !this.isFree(banPhong.getId())) {
				return new Sms("0", "Đã xảy ra lỗi!");
			} else {
				BanLe banLe = new BanLe(this.getNewID(), currentDate, donDatHang.getNoiDung(), 0, banPhong,
						donDatHang.getKhachHang(), donDatHang.getSoKhach(), thuNgan, null,(long) donDatHang.getGiam(), 0, 0,
						donDatHang.getSoTien(), donDatHang.getTraTruoc(), 0);
				List<DongChungTu> thucDon = new ArrayList<DongChungTu>(donDatHang.getDongChungTu());
				banLeRepository.save(banLe);
				for(DongChungTu dongChungTu:thucDon) {
					dongChungTu.setChungTu(banLe);
				}
				dongChungTuRepository.saveAll(thucDon);
				donDatHang.setTrangThai(1);
				donDatHangRepository.save(donDatHang);
				sms1 = new Sms("1", "Thành công");
			}
		} catch (DataAccessException e) {
			sms1 = new Sms("-1", "DataAccessException : DonDatHangService delete(" + sms + ")");
			System.out.println(sms.getStatus());
		} catch (Exception e) {
			sms1 = new Sms("-1", "Exception : DonDatHangService delete(" + sms + ")");
			System.out.println(sms.getStatus());
		}
		return sms1;
	}
	

	@Override
	public List<BanLe> getChungTuByDate(String from, String to) {
		Date fromDate = null, toDate = null;
		final String OLD_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		try {
			fromDate = sdf.parse(from);
			toDate = sdf.parse(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance(); 
		c.setTime(toDate); 
		c.add(Calendar.DAY_OF_MONTH , 1);
		toDate = c.getTime();
		System.out.println(fromDate +"              "+toDate);
		List<BanLe>	banLe = banLeRepository.findBetween(fromDate, toDate);
		return banLe;
	}

	private String getNewID() {
		BanLe donDatHang = banLeRepository.findTheLast();
		if (donDatHang == null || donDatHang.getId().length() < 5) {
			return "00001BL";
		} else {
			int newID = Integer.parseInt(donDatHang.getId().substring(0, 5));
			String idFormat;
			while (true) {
				newID = newID + 1;
				idFormat = String.format("%05d", newID);
				if (!banLeRepository.existsById(idFormat + "BL")) {
					break;
				}
			}
			return idFormat + "BL";
		}
	}

	private boolean isFree(String banPhongId) {
		if (banLeRepository.countByTrangThai(0, banPhongId) > 0) {
			return false;
		}
		return true;
	}



//	private String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
//		// (date nhập vào, format date nhập vào, format date return )
//		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
//		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
//		String parsedDate = formatter.format(initDate);
//		return parsedDate;
//	}

}
