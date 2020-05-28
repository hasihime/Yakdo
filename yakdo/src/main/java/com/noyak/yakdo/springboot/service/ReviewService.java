package com.noyak.yakdo.springboot.service;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.pharmacy.PharmacyRepository;
import com.noyak.yakdo.springboot.domain.review.Review;
import com.noyak.yakdo.springboot.domain.review.ReviewRepository;
import com.noyak.yakdo.springboot.web.dto.review.ReviewSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줌
// => Autowired 없이도 가능
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public boolean save(ReviewSaveRequestDto requestDto) {
        System.out.println(requestDto.toString());
        int pharmId = requestDto.getP_id();
        try {
            // 약국받아오기 - 여기서 Optional은 Object같은 것
            Optional<Pharmacy> pharmacy = pharmacyRepository.findById(pharmId);
            Pharmacy pharm = pharmacy.get(); // 약국 찾기
            Review review = Review.builder()
                    .r_writer(requestDto.getR_writer())
                    .r_pw(requestDto.getR_pw())
                    .r_content(requestDto.getR_content())
                    .p_id(pharm)
                    .build();
            if (review == null) {
                return false;
            }
            reviewRepository.save(review);
            return true;
        } catch (RuntimeException e) {
            logger.error("리뷰 등록 실패", e);
            return false;
        }
    }

    public Long findReviewId(int p_id) {
        Long r_id = 0l;
        Pharmacy pharm = pharmacyRepository.findById(p_id).get();
        r_id = reviewRepository.findReviewById(pharm);
        return r_id;
    }
}
