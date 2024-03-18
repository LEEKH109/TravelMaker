package com.a305.travelmaker.domain.province.service;

import com.a305.travelmaker.domain.province.repository.ProvinceRepository;
import com.a305.travelmaker.domain.province.entity.Province;
import com.a305.travelmaker.domain.province.dto.ProvinceResponse;
import com.a305.travelmaker.global.util.FileService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProvinceService {

  private final ProvinceRepository provinceDao;
  private final FileService fileApplication;

  // 도 리스트 조회
  public List<ProvinceResponse> findProvinceList() {

    List<ProvinceResponse> provinceResponses = new ArrayList<>();
    List<Province> provinces = provinceDao.findAll();

    for (Province province : provinces) {
      ProvinceResponse response = ProvinceResponse.builder()
          .provinceId(province.getId())
          .provinceName(province.getName())
          .provinceUrl(province.getImgUrl())
          .build();
      provinceResponses.add(response);
    }
    return provinceResponses;
  }

  // 도 데이터 저장 (테스트 용)
  public void saveProvince(MultipartFile file, String name) {

    String imgUrl = fileApplication.uploadFile(file);

    provinceDao.save(Province.builder()
            .name(name)
            .imgUrl(imgUrl)
            .build());
  }
}
