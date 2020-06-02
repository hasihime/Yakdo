package com.noyak.yakdo.springboot.service;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.pharmacy.PharmacyRepository;
import com.noyak.yakdo.springboot.web.dto.pharmacy.PharmacyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
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
    public List<PharmacyResponseDto> findByPosition(double p_x, double p_y) {


//        List<PharmacyResponseDto> list = pharmacyRepository // 여기 오른쪽이 타입이 뜨잖아
//                .findAllDesc(p_x, p_y)
//                .stream()
//                .map(PharmacyResponseDto::new)
//                .collect(Collectors.toList());

        List<Pharmacy> lp = pharmacyRepository.findByPosition(p_x, p_y);
        List<PharmacyResponseDto> list = new ArrayList<>();
        int lpsize = lp.size();

        LocalDateTime time = LocalDateTime.now();
        int minutes = time.getHour()*60 + time.getMinute();

        for (int i = 0; i < lpsize; i++) {
            String oper = lp.get(i).getP_oper();
            // 09:00 ~ 익일06:00
            // 012345678 901234
            // 만약 새벽 3시라면 ?

            // 23:00 ~
            int start = Integer.parseInt(oper.substring(0,2))*60 + Integer.parseInt(oper.substring(3,5));
            int end = 0;
            if(oper.contains("익일")){
                end += Integer.parseInt(oper.substring(10,12))*60;
                end += Integer.parseInt(oper.substring(13));

                // start ~ 24 시까지
                if(start <= minutes && minutes < 24*60) {
                    list.add(new PharmacyResponseDto(lp.get(i)));
                    continue;
                }
                // 00시 부터 익일 end 시 까지
                if(0 <= minutes && minutes < end) {
                    list.add(new PharmacyResponseDto(lp.get(i)));
                    continue;
                }

            } else { // 익일이 없으면 단순하게 시간 내부에 존재하면 운영중
                end += Integer.parseInt(oper.substring(8,10))*60;
                end += Integer.parseInt(oper.substring(11));
                if(start <= minutes && minutes < end) {
                    list.add(new PharmacyResponseDto(lp.get(i)));
                    continue;
                }
            }
        }

        // 리스트를 거리에 따라 정렬
        // https://m.blog.naver.com/PostView.nhn?blogId=jinohpark79&logNo=221170630625&proxyReferer=https:%2F%2Fwww.google.com%2F
        // 위도 35도에서 위도 1도에 110 km, 경도 1도에 90 km

        int size = list.size();

        for (int i = 0; i < size; i++) {
            // p_x, p_y 와 i객체 간의 거리 계산
            list.get(i).calc(p_x, p_y);
        }

        Collections.sort(list, new Comparator<PharmacyResponseDto>() {
            @Override
            public int compare(PharmacyResponseDto o1, PharmacyResponseDto o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });

        return list;
    }

    @Transactional(readOnly = true)
    public List<PharmacyResponseDto> findWithAddress(String address, double p_x, double p_y) {

        List<Pharmacy> lp = pharmacyRepository.findWithAddress(address);

        int lpsize = lp.size();

        List<PharmacyResponseDto> list = new ArrayList<>();

        LocalDateTime time = LocalDateTime.now();
        int minutes = time.getHour()*60 + time.getMinute();

        for (int i = 0; i < lpsize; i++) {
            String oper = lp.get(i).getP_oper();
            // 09:00 ~ 익일06:00
            // 012345678 901234
            // 만약 새벽 3시라면 ?

            // 23:00 ~
            int start = Integer.parseInt(oper.substring(0,2))*60 + Integer.parseInt(oper.substring(3,5));
            int end = 0;
            if(oper.contains("익일")){
                end += Integer.parseInt(oper.substring(10,12))*60;
                end += Integer.parseInt(oper.substring(13));

                // start ~ 24 시까지
                if(start <= minutes && minutes < 24*60) {
                    list.add(new PharmacyResponseDto(lp.get(i)));
                    continue;
                }
                // 00시 부터 익일 end 시 까지
                if(0 <= minutes && minutes < end) {
                    list.add(new PharmacyResponseDto(lp.get(i)));
                    continue;
                }

            } else { // 익일이 없으면 단순하게 시간 내부에 존재하면 운영중
                end += Integer.parseInt(oper.substring(8,10))*60;
                end += Integer.parseInt(oper.substring(11));
                if(start <= minutes && minutes < end) {
                    list.add(new PharmacyResponseDto(lp.get(i)));
                    continue;
                }
            }
        }

        // 리스트를 거리에 따라 정렬
        // https://m.blog.naver.com/PostView.nhn?blogId=jinohpark79&logNo=221170630625&proxyReferer=https:%2F%2Fwww.google.com%2F
        // 위도 35도에서 위도 1도에 110 km, 경도 1도에 90 km

        int size = list.size();

        for (int i = 0; i < size; i++) {
            // p_x, p_y 와 i객체 간의 거리 계산
            list.get(i).calc(p_x, p_y);
        }

        Collections.sort(list, new Comparator<PharmacyResponseDto>() {
            @Override
            public int compare(PharmacyResponseDto o1, PharmacyResponseDto o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });

        return list;
    }

}



