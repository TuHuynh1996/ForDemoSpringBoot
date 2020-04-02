package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IKhuVucService;
import com.example.demo.formClass.AddKhuVucForm;
import com.example.demo.formClass.Sms;
import com.example.demo.model.banphong.KhuVuc;
import com.example.demo.repository.KhuVucRepository;
@Service
public class KhuVucService implements IKhuVucService {
	@Autowired
	KhuVucRepository khuVucRepository;
	@Override
	public List<KhuVuc> getAll() {
		List<KhuVuc> khuVuc= new ArrayList<KhuVuc>();
		khuVucRepository.findAll().forEach(khuVuc::add);
		return khuVuc;
	}
	@Override
	public Sms add(AddKhuVucForm khuVuc) {
		Sms status;
		try {
			List<KhuVuc> khuVucArray = new ArrayList<KhuVuc>();
			for(int i = 0; i < khuVuc.getId().size(); i++) {
				khuVucArray.add(new KhuVuc(khuVuc.getId().get(i), khuVuc.getTenKhuVuc().get(i)));
			}
			khuVucRepository.saveAll(khuVucArray);
			status = new Sms("1", "Thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: KhuVucService.add(int "+khuVuc+")");
			status = new Sms("-1", "Lỗi");
		} catch (Exception e) {
			System.out.println("Exception: KhuVucService.add(int "+khuVuc+")");
			status = new Sms("-1", "Lỗi");
		}
		
		return status;
	}
	@Override
	public Sms delete(String id) {
		Sms status;
		try {
			khuVucRepository.deleteById(id);
			status = new Sms("1", "Thành công");
		} catch (DataAccessException e) {
			System.out.println("DataAccessException: KhuVucService.delete(int "+id+")");
			status = new Sms("-1", "Lỗi");
		}catch (Exception e) {
			System.out.println("Exception: KhuVucService.delete(int "+id+")");
			status = new Sms("-1", "Lỗi");
		}
		
		return status;
	}
}
//try {
//	
//} catch (DataAccessException e) {
//	System.out.println("DataAccessException: KhuVucService.add(int "+khuVuc+")");
//	status = new Sms("-1", "Lỗi");
//}
