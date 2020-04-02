package com.example.demo.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IDonDatHangService;
import com.example.demo.formClass.Sms;
import com.example.demo.formClass.DatBanForm.DatBanForm;
import com.example.demo.formClass.DatBanForm.ThucDonForm;
import com.example.demo.model.chungtu.DonDatHang;
import com.example.demo.model.chungtu.DongChungTu;
import com.example.demo.model.khachhang.KhachHang;
import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.SanPham;
import com.example.demo.repository.DVTKhacRepository;
import com.example.demo.repository.DonDatHangRepository;
import com.example.demo.repository.DongChungTuRepository;
import com.example.demo.repository.KhachHangRepository;
import com.example.demo.repository.SanPhamRepository;

@Service
public class DonDatHangService implements IDonDatHangService {
	@Autowired
	DonDatHangRepository donDatHangRepository;
	@Autowired
	KhachHangRepository khachHangRepository;
	@Autowired
	DongChungTuRepository dongChungTuRepository;
	@Autowired
	SanPhamRepository sanPhamRepository;
	@Autowired
	DVTKhacRepository dvtKhacRepository;
	

	@Override
	public Sms save(DatBanForm datBan) {
		Sms sms = new Sms("0", "Số lượng thực đơn không được âm");
		List<ThucDonForm> thucDon = new ArrayList<ThucDonForm>(datBan.getThucDon());
		List<DongChungTu> dongChungTu = new ArrayList<DongChungTu>();
		Date currentDate = new Date(); // lấy thời gian thực
		DonDatHang donDatHang = null;
		String loaiBangGiaId = datBan.getBangGia();
		try {

			long soTien = 0;
			// Xử lý thực đơn
			for (ThucDonForm thucDonForm : thucDon) {
				if (thucDonForm.getSoLuong() < 0) {
					return new Sms("0", "Số lượng thực đơn không được âm");
				}
				String[] id = thucDonForm.getId().split("-");
				if (id[0].equals("dvtkhac")) {
					DVTKhac donViTinhKhac = dvtKhacRepository.findById(Integer.parseInt(id[1])).get();
					long donGia = (long) (donViTinhKhac.getSanPham().getDonGiaByLoaiBangGia(loaiBangGiaId)
							* thucDonForm.getSoLuong());
					dongChungTu.add(new DongChungTu(null, null, donViTinhKhac, (double) thucDonForm.getSoLuong(),
							donGia, "", "", 0, 0, thucDonForm.getGhiChu()));
					soTien = soTien + donGia;
				} else {
					SanPham sanPham = sanPhamRepository.findById(thucDonForm.getId()).get();
					long donGia = (long) (sanPham.getDonGiaByLoaiBangGia(loaiBangGiaId) * thucDonForm.getSoLuong());
					dongChungTu.add(new DongChungTu(null, null, sanPham, (double) thucDonForm.getSoLuong(), donGia, "",
							"", 0, 0, thucDonForm.getGhiChu()));
					soTien = soTien + donGia;
				}
			}
			
			KhachHang khachHang = khachHangRepository.findById(datBan.getKhachHang()).get();
			String formatDate = formatDate(datBan.getNgayDat(), "MM/dd/yyyy", "yyyy/MM/dd");
			Date ngayDat = new SimpleDateFormat("yyyy/MM/dd").parse(formatDate);
			if (datBan.getId().equals("")) {
				donDatHang = new DonDatHang(getNewID(), currentDate, datBan.getGhiChu(), 0, khachHang, ngayDat,
						datBan.getGioDat(), datBan.getSoKhach(), soTien, datBan.getGiam(), datBan.getTienDatTruoc());
			} else {
				Set<DongChungTu> deleteDongChungTu = donDatHangRepository.findById(datBan.getId()).get().getDongChungTu();
				dongChungTuRepository.deleteAll(deleteDongChungTu);
				 DonDatHang checkDonDatHang = donDatHangRepository.findById(datBan.getId()).get();
				 if(checkDonDatHang.getTrangThai() == 1) {
					 return new Sms("0", "Bạn không thể chỉnh sửa chứng từ");
				 }else {
					 donDatHang = new DonDatHang(datBan.getId(), currentDate, datBan.getGhiChu(), 0, khachHang, ngayDat,
								datBan.getGioDat(), datBan.getSoKhach(), soTien, datBan.getGiam(), datBan.getTienDatTruoc());
				 }
			}
			DonDatHang chungTuSaved = donDatHangRepository.save(donDatHang);
			for (DongChungTu value : dongChungTu) {
				value.setChungTu(chungTuSaved);
			}
			dongChungTuRepository.saveAll(dongChungTu);
			sms = new Sms("1", "Thêm thành công");
		} catch (DataAccessException e) {
			sms = new Sms("-1", "DataAccessException : DonDatHangService save(" + datBan + ")");
			System.out.println(sms.getStatus());
		} catch (Exception e) {
			sms = new Sms("-1", "Exception : DonDatHangService save(" + datBan + ")");
			System.out.println(sms.getStatus());
			e.printStackTrace();
		}
		return sms;
	}
	

	@Override
	public Sms delete(Sms sms) {
		Sms sms1;
		try {
			DonDatHang donDatHang = donDatHangRepository.findById(sms.getId()).get();
			dongChungTuRepository.deleteAll(donDatHang.getDongChungTu());
			donDatHangRepository.delete(donDatHang);
			sms1 = new Sms("1", "xóa thành công");
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
	public List<DonDatHang> getAll() {
		List<DonDatHang> donDatHanga = donDatHangRepository.getAll();
		return donDatHanga;
	}


//////////////////////////////////////////////////////
	private String getNewID() {
		DonDatHang donDatHang = donDatHangRepository.findTheLast();
		if (donDatHang == null || donDatHang.getId().length() < 5) {
			return "00001DDH";
		} else {
			int newID = Integer.parseInt(donDatHang.getId().substring(0, 5));
			String idFormat;
			while (true) {
				newID = newID + 1;
				idFormat = String.format("%05d", newID);
				if (!donDatHangRepository.existsById(idFormat + "DDH")) {
					break;
				}
			}
			return idFormat + "DDH";
		}
	}

	public String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
		// (date nhập vào, format date nhập vào, format date return )
		Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String parsedDate = formatter.format(initDate);
//	    return new SimpleDateFormat(endDateFormat).parse(parsedDate); 
		return parsedDate;
	}



}
