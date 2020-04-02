package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IBoPhanService;
import com.example.demo.formClass.Sms;
import com.example.demo.model.nhanvien.BoPhan;
import com.example.demo.repository.BoPhanRepository;
import com.example.demo.repository.NhanVienRepository;

@Service
public class BoPhanService implements IBoPhanService {
	@Autowired
	BoPhanRepository boPhanRepository;
	@Autowired
	NhanVienRepository nhanVienRepository;
	
	@Override
	public List<BoPhan> getList() {
		List<BoPhan> bp = new ArrayList<BoPhan>();
		boPhanRepository.findAll().forEach(bp::add);;
		List<BoPhan> bp1 = new ArrayList<BoPhan>();
		for (int i = 0; i < bp.size(); i++) {
			bp.get(i).setCon(bp);
			if (bp.get(i).getMaCha().equals("0")) {
				bp1.add(bp.get(i));
			}
		}
		return bp1;
	}
	@Override
	public Sms saveBP(BoPhan boPhan) {
		Sms status;
		try {
			if(!boPhan.getId().equals("")) {
				status = new Sms(boPhan.getId(),"Sửa thành công");
				boPhanRepository.save(boPhan);
			}else {
				if(boPhanRepository.existsById(boPhan.getMaCha()) || boPhan.getMaCha().equals("0")) {
					int id = Integer.parseInt(this.getThelast().getId());
					String aid;
					while(true) {
						id = id+ 1;
						aid = String.format("%03d", id);
						if(!boPhanRepository.existsById(aid)) {
							break;
						}
					}
					boPhan.setId(aid);
					status = new Sms(id+"","Thêm thành công");
					boPhanRepository.save(boPhan);
				}else {
					status = new Sms("0","Thư mục cha không tồn tại hoặc đã bị xóa từ trước");
				}
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: BoPhanService saveBP("+boPhan+")");
			status = new Sms("-1","Lỗi");
		} catch(Exception e) {
			System.out.println("Exception: BoPhanService saveBP("+boPhan+")");
			status = new Sms("-1","Lỗi");
		}
		return status;
	}
	
	@Override
	public Sms deteleBoPhan(BoPhan boPhan) {
		Sms status;
		try {
			if (!this.existsByBoPhanId(boPhan.getId()) && !this.existsByMaCha(boPhan.getId())) {
				if (boPhanRepository.existsById(boPhan.getId())) {
					boPhanRepository.deleteById(boPhan.getId());
					status = new Sms("1", "Xóa thành công!");
				} else {
					status = new Sms("0", "Bộ phận này không tồn tại!");
				}
			} else {
				status = new Sms("0", "Không thể xóa thư mục chứa nhân viên hoặc chứa thư mục con!");
			}
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: BoPhanService saveBP("+boPhan+")");
			status = new Sms("-1","Lỗi");
		} catch(Exception e) {
			System.out.println("Exception: BoPhanService saveBP("+boPhan+")");
			status = new Sms("-1","Lỗi");
		}
		
		return status;
	}
	
/////////////////////////////////////////////////////////////////	

	private boolean existsByMaCha(String id) {
		int a = boPhanRepository.countByMaCha(id);
		return a!=0?true:false;
	}
	
	private BoPhan getThelast() {
		BoPhan bp = boPhanRepository.findTheLastBP();
		if(bp != null) {
			return bp;
		}else {
			return new BoPhan("0", null, null);
		}
	}
	private boolean existsByBoPhanId(String id) {
		int a = nhanVienRepository.countByBoPhanId(id);
		return a!=0?true:false;
	}
}
