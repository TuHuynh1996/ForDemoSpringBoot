package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.IService.IBangGiaService;
import com.example.demo.IService.IDVTKhacService;
import com.example.demo.IService.IDonViTinhService;
import com.example.demo.IService.ILoaiBanGiaService;
import com.example.demo.IService.INhomHangService;
import com.example.demo.IService.ISanPhamService;
import com.example.demo.IService.IThanhPhanService;
import com.example.demo.formClass.AddDonViTinhKhacForm;
import com.example.demo.formClass.DonViTinhKhacEditForm;
import com.example.demo.formClass.MatHangForm;
import com.example.demo.formClass.Sms;
import com.example.demo.formClass.ThanhPhanEditForm;
import com.example.demo.formClass.ThanhPhanForm;
import com.example.demo.model.sanpham.DVTKhac;
import com.example.demo.model.sanpham.DonViTinh;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.example.demo.model.sanpham.NhomHang;
import com.example.demo.model.sanpham.ThanhPhan;

@Controller
public class SanPhamController {
	@Autowired
	ISanPhamService sanPhamService;
	@Autowired
	INhomHangService nhomHangService;
	@Autowired
	ILoaiBanGiaService loaiBanGiaService;
	@Autowired
	IDonViTinhService donViTinhService;
	@Autowired
	IBangGiaService bangGiaService;
	@Autowired
	IThanhPhanService thanhPhanService;
	@Autowired
	IDVTKhacService dvtKhacService;
//	@RequestMapping(value = "/ajax/sanpham/listsp")
//	@ResponseBody
//	public List<SanPham> getAll() {
//		List<SanPham> sp = sanPhamService.getAll();
//		return sp;
//	}

	@RequestMapping(value = "/ajax/mathang/listnhomhang")
	public String nhomSanPham(Model model) {
		model.addAttribute("nhomhang", nhomHangService.getList());
		return "content/mathang/listnhomhang";
	}

	@RequestMapping(value = "/ajax/mathang/listmathang/{id}/", method = RequestMethod.GET)
	public String listSanPham(@PathVariable("id") String id, Model model) {
		List<LoaiBangGia> lbg;
		List<DonViTinh> dvt;
		lbg = loaiBanGiaService.getAll();
		Collections.sort(lbg);
		dvt = donViTinhService.getAll();
		model.addAttribute("dvt", dvt);
		model.addAttribute("lbg", lbg);
		model.addAttribute("sanpham", sanPhamService.getList(id));
		return "content/mathang/listmathang";
	}

	@RequestMapping(value = "/ajax/mathang/savesp", method = RequestMethod.POST)
	public ResponseEntity<?> saveSanPham(@RequestBody MatHangForm sanPhamForm) {
		Sms status = sanPhamService.saveSanPham(sanPhamForm);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/deletesp", method = RequestMethod.POST)
	public ResponseEntity<?> deleteSanPham(@RequestBody Sms sanPham) {
		Sms status = sanPhamService.deleteSanPham(sanPham.getId());
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/savenhomhang", method = RequestMethod.POST)
	public ResponseEntity<?> saveNhomHang(@RequestBody NhomHang nhomHang) {
		Sms status = nhomHangService.saveNhomHang(nhomHang);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/deletenhomhang", method = RequestMethod.POST)
	public ResponseEntity<?> deletesp(@RequestBody NhomHang nhomHang) {
		Sms status = nhomHangService.deteleNhomHang(nhomHang);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/chuyennhomhang", method = RequestMethod.POST)
	public ResponseEntity<?> changebp(@RequestBody Sms nhanVien) {
		Sms status = sanPhamService.changeFolder(nhanVien);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	/////////////////////////////////////////////////////////////////////
	//////////////// Dữ liệu ////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/ajax/mathang/loaibanggia")
	public String loadBangGia(Model model) {
		model.addAttribute("lbg", loaiBanGiaService.getAll());
		return "/content/mathang/banggiabanhang";
	}

	@RequestMapping(value = "/ajax/mathang/donvitinh")
	public String loadDVT(Model model) {
		model.addAttribute("dvt", donViTinhService.getAll());
		return "/content/mathang/donvitinh";
	}

	@RequestMapping(value = "/ajax/mathang/editlbg", method = RequestMethod.POST)
	public ResponseEntity<?> editLBG(@RequestBody LoaiBangGia loaiBangGia) {
		Sms status = loaiBanGiaService.edit(loaiBangGia);
		return new ResponseEntity<Sms>(loaiBanGiaService.edit(loaiBangGia), status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/addlbg", method = RequestMethod.POST)
	public ResponseEntity<?> addLBG(@RequestBody LoaiBangGia loaiBangGia) {
		Sms status = loaiBanGiaService.add(loaiBangGia);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/deletelbg", method = RequestMethod.POST)
	public ResponseEntity<?> deleteLBG(@RequestBody LoaiBangGia loaiBangGia) {
		Sms status = loaiBanGiaService.delete(loaiBangGia);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/savedvt", method = RequestMethod.POST)
	public ResponseEntity<?> editDVT(@RequestBody DonViTinh dvt) {
		Sms status = donViTinhService.saveDVT(dvt);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/deletedvt", method = RequestMethod.POST)
	public ResponseEntity<?> deleteDVT(@RequestBody DonViTinh dvt) {
		Sms status = donViTinhService.deteleDVT(dvt);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}
	/////////////////////////// End ///////////////////////////////////////////

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////// Chi Tiet////////////////////////////////////
	///////////////////////////// ////////////////////////////////////////////

	@RequestMapping(value = "/ajax/mathang/chitiet/{id}/", method = RequestMethod.GET)
	public String chiTiet(@PathVariable("id") String id, Model model) {
		model.addAttribute("sanpham", sanPhamService.getbyId(id));
		return "/content/mathang/chitiet/chitietsanpham";
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/thanhphan/{id}/", method = RequestMethod.GET)
	public String thanhPhanChiTiet(@PathVariable("id") String id, Model model) {
		List<ThanhPhan> thanhPhan = new ArrayList<ThanhPhan>(sanPhamService.getbyId(id).getThanhPhan());
		Collections.sort(thanhPhan, new Comparator<ThanhPhan>() {
			@Override
			public int compare(ThanhPhan o1, ThanhPhan o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		model.addAttribute("thanhphan", thanhPhan);
		model.addAttribute("thanhphanall", sanPhamService.getAllThanhPhan());
		model.addAttribute("dvt", donViTinhService.getAll());
		return "/content/mathang/chitiet/thanhphan";
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/luuthanhphan")
	public ResponseEntity<?> saveThanhPhan(@RequestBody ThanhPhanForm thanhPhan) {
		Sms status = thanhPhanService.saveThanhPhan(thanhPhan);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/thanhphan/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> deleteThanhPhan(@PathVariable("id") String id) {
		Sms status = thanhPhanService.deleteById((Integer) Integer.parseInt(id));
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/thanhphan/edit")
	public ResponseEntity<?> editThanhPhan(@RequestBody ThanhPhanEditForm thanhPhan) {
		ThanhPhanEditForm thanhPhanEditForm = thanhPhanService.edit(thanhPhan);
		HttpStatus httpStatus;
		if (thanhPhanEditForm.getId() == -1) {
			httpStatus = HttpStatus.BAD_REQUEST;
		} else {
			httpStatus = HttpStatus.OK;
		}
		return new ResponseEntity<ThanhPhanEditForm>(thanhPhanEditForm, httpStatus);
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/chuyendoi/{id}/", method = RequestMethod.GET)
	public String chuyenDoiChiTiet(@PathVariable("id") String id, Model model) {
		List<DVTKhac> donViTinhKhac = new ArrayList<DVTKhac>(sanPhamService.getbyId(id).getDvtKhac());
		Collections.sort(donViTinhKhac, new Comparator<DVTKhac>() {
			@Override
			public int compare(DVTKhac o1, DVTKhac o2) {
				return o1.getId() - o2.getId();
			}
		});
		model.addAttribute("dvtkhac", donViTinhKhac);
		model.addAttribute("lbg", loaiBanGiaService.getAll());
		model.addAttribute("dvt", donViTinhService.getAll());
		return "/content/mathang/chitiet/chuyendoi";
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/chuyendoi/add")
	public ResponseEntity<?> addDonViTinhKhac(@RequestBody AddDonViTinhKhacForm donViTinhForm) {
		Sms status = dvtKhacService.add(donViTinhForm);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/chuyendoi/delete/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> addDonViTinhKhac(@PathVariable("id") Integer id) {
		Sms status = dvtKhacService.delete(id);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

	@RequestMapping(value = "/ajax/mathang/chitiet/chuyendoi/edit")
	public ResponseEntity<?> editDonViTinhKhac(@RequestBody DonViTinhKhacEditForm donViTinhKhacEditForm) {
		Sms status = dvtKhacService.edit(donViTinhKhacEditForm);
		return new ResponseEntity<Sms>(status, status.getStatusHttp());
	}

}
