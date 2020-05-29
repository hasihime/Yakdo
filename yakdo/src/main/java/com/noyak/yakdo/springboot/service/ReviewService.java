package com.noyak.yakdo.springboot.service;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.pharmacy.PharmacyRepository;
import com.noyak.yakdo.springboot.domain.review.Review;
import com.noyak.yakdo.springboot.domain.review.ReviewRepository;
import com.noyak.yakdo.springboot.web.dto.review.ReviewModifyRequestDto;
import com.noyak.yakdo.springboot.web.dto.review.ReviewResponseDto;
import com.noyak.yakdo.springboot.web.dto.review.ReviewSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

//  리뷰 전체 조회
    public List<ReviewResponseDto> all(int p_id) {
        List<Review> reviews = null;
        List<ReviewResponseDto> returnReviews = new ArrayList<>();

        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(p_id);
        Pharmacy pharm = pharmacy.get(); // 약국 찾기
        try {
            reviews = reviewRepository.findByPharmacy(pharm);
            System.out.println(reviews.toString());
            for (Review review : reviews) {
                ReviewResponseDto newReview = ReviewResponseDto.builder()
                        .r_id(review.getR_id())
                        .r_writer(review.getR_writer())
                        .r_content(review.getR_content())
                        .build();
                returnReviews.add(newReview);
            }
        } catch (RuntimeException e) {
            logger.error("리뷰 조회 실패");
            throw e;
        }
        return returnReviews;
    }

    public ReviewResponseDto find(long r_id) {
        ReviewResponseDto  returnReview = null;
        try {
            Review review = reviewRepository.findReviewByReviewId(r_id);
            returnReview = ReviewResponseDto.builder()
                    .r_id(review.getR_id())
                    .r_writer(review.getR_writer())
                    .r_content(review.getR_content())
                    .build();
        } catch (RuntimeException e) {
            logger.error("리뷰 조회 실패");
            throw e;
        }
        return returnReview;
    }

    // 리뷰 수정
    @Transactional
    public boolean modify(long id, ReviewModifyRequestDto requestDto) {
        Long r_id = 0l;
        System.out.println(requestDto.toString());
        try {
            Review review = reviewRepository.findById(id).get();
            // 비밀번호 일치 여부 판단
            if(requestDto.getR_pw().equals(review.getR_pw())) {
                review.update(requestDto.getR_content());
            } else {
                return false;
            }
        } catch (RuntimeException e) {
            logger.error("리뷰 수정 실패");
            throw e;
        }
        return true;
    }

    @Transactional
    public boolean remove(ReviewSaveRequestDto requestDto) {
        Long r_id = 0l;
        System.out.println(requestDto.toString());

        try {
            Pharmacy pharm = pharmacyRepository.findById(requestDto.getP_id()).get();
            r_id = reviewRepository.findReviewById(pharm);
            // 기존 리뷰 찾기
            Review review = reviewRepository.findById(r_id).get();
            // 패스워드 일치 여부
            if(requestDto.getR_pw().equals(review.getR_pw())) {
                reviewRepository.delete(review);
            } else {
                return false;
            }
        } catch (RuntimeException e) {
            logger.error("리뷰 삭제 실패");
            throw e;
        }
        return true;
    }
}
