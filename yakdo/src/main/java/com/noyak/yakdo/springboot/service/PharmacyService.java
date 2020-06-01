package com.noyak.yakdo.springboot.service;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.pharmacy.PharmacyRepository;
import com.noyak.yakdo.springboot.web.dto.pharmacy.PharmacyListResponseDto;
import com.noyak.yakdo.springboot.web.dto.pharmacy.PharmacyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    //약국 Detail 정보 받아올 때 사용.
    @Transactional(readOnly = true)
    public PharmacyResponseDto findById(int id) {
        Pharmacy entity = pharmacyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PharmacyResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PharmacyListResponseDto> findAllDesc(double p_x, double p_y) {

        // 이거 해결이 안되는데
        // 다른 사람거 따라한거라서 어디서 오류가난건지 모르겠어
        // 그냥 CRUD 중에서 리스트 get 받아오는거 만드는 중이었는데
        // 일단 약국 전체

        // 이거 리뷰 따라한건데
        // 아 그래?
        return List<PharmacyListResponseDto> PharmacyRepository
                .findAllDesc(p_x, p_y)
                .stream()
                .map(PharmacyListResponseDto::new)
                .collect(Collectors.toList());

    }

}



