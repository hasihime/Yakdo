package com.noyak.yakdo.springboot.service;
import com.noyak.yakdo.springboot.domain.pharmacy.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

}
