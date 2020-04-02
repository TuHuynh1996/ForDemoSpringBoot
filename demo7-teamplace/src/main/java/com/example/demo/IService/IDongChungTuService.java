package com.example.demo.IService;

import java.util.List;

import com.example.demo.model.chungtu.DongChungTu;

public interface IDongChungTuService {

	List<DongChungTu> getDongChungTuByChungTuId(String id);

	List<DongChungTu> getDongChungTuByChungTuIdBangGia(String id, String bangGia);

}
