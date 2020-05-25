package com.noyak.yakdo.springboot.service;
import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.pharmacy.PharmacyRepository;
import com.noyak.yakdo.springboot.web.dto.pharmacy.PharmacyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
