package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IBanPhongService;
import com.example.demo.formClass.AddBanPhongForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.banphong.BanPhong;
import com.example.demo.model.banphong.KhuVuc;
import com.example.demo.model.sanpham.LoaiBangGia;
import com.example.demo.repository.BanLeRepository;
import com.example.demo.repository.BanPhongRepository;
import com.example.demo.repository.KhuVucRepository;
import com.example.demo.repository.LoaiBangGiaRepository;

@Service
public class BanPhongService implements IBanPhongService {
	@Autowired
	BanPhongRepository banPhongRepository;
	@Autowired
	KhuVucRepository khuVucRepository;
	@Autowired
	LoaiBangGiaRepository loaiBangGiaGiaRepository;
	@Autowired
	BanLeRepository banLeRepository;

	@Override
	public List<BanPhong> getAll() {
		List<BanPhong> banPhongList = new ArrayList<BanPhong>();
		banPhongRepository.findAll().forEach(banPhongList::add);
		Collections.sort(banPhongList, new Comparator<BanPhong>() {
			@Override
			public int compare(BanPhong o1, BanPhong o2) {
				return o2.getMaKV().getId().compareTo(o1.getMaKV().getId());
			}
		});
		return banPhongList;
	}

	@Override
	public List<BanPhong> getAllMenu() {
		List<BanPhong> banPhongList = banPhongRepository.getAllBanPhongMenu();
		for (BanPhong banPhong : banPhongList) {
			banPhong.setChungTuUsing(banLeRepository.getBanLeByBanPhongId(banPhong.getId()));
		}
		Collections.sort(banPhongList, new Comparator<BanPhong>() {
			@Override
			public int compare(BanPhong o1, BanPhong o2) {
				return o2.getMaKV().getId().compareTo(o1.getMaKV().getId());
			}
		});
		return banPhongList;
	}

	@Override
	public Sms add(AddBanPhongForm banPhong) {
		Sms status;
		List<BanPhong> banPhongList = new ArrayList<BanPhong>();
		try {
			for (int i = 0; i < banPhong.getId().size(); i++) {
				KhuVuc khuVuc = khuVucRepository.findById(banPhong.getKhuVuc().get(i)).get();
				LoaiBangGia bangGia = loaiBangGiaGiaRepository.findById(banPhong.getBangGia().get(i)).get();
				banPhongList.add(new BanPhong(banPhong.getId().get(i), khuVuc, bangGia, banPhong.getStatus().get(i)));
			}
			banPhongRepository.saveAll(banPhongList);
			status = new Sms("1", "Thêm thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: BanPhongService.add(int " + banPhong.getId() + ")");
			status = new Sms("-1", "Đã xảy ra lỗi!");
		} catch (Exception e) {
			System.out.println("Exception: BanPhongService.add(int " + banPhong.getId() + ")");
			status = new Sms("-1", "Đã xảy ra lỗi!");
		}
		return status;
	}

	@Override
	public Sms delete(String id) {
		Sms status;
		try {
			banPhongRepository.deleteById(id);
			status = new Sms("1", "Thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: BanPhongService.delete(int " + id + ")");
			status = new Sms("-1", "Đã xảy ra lỗi!");
		}
		return status;
	}

}
