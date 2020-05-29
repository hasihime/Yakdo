package com.noyak.yakdo.springboot.web;

import com.noyak.yakdo.springboot.result.BoolResult;
import com.noyak.yakdo.springboot.result.LongResult;
import com.noyak.yakdo.springboot.service.ReviewService;
import com.noyak.yakdo.springboot.web.dto.review.ReviewModifyRequestDto;
import com.noyak.yakdo.springboot.web.dto.review.ReviewResponseDto;
import com.noyak.yakdo.springboot.web.dto.review.ReviewSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin({"*"})
@RequestMapping("/review") // 앞에 공통으로 붙는 url
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/find/{r_id}")
    public ReviewResponseDto findReview(@PathVariable long r_id) {
        ReviewResponseDto review = null;
        try {
            review = reviewService.find(r_id);
        } catch (RuntimeException e) {
            log.error("findReview", e);
            throw e;
        }
        return review;
    }

    @GetMapping("/all/{p_id}")
    public List<ReviewResponseDto> findAllReview(@PathVariable int p_id) {
        List<ReviewResponseDto> reviews = null;
        System.out.println("약국 : "+p_id);
        try {
            // 각 약국마다 조회
            reviews = reviewService.all(p_id);
        } catch (RuntimeException e) {
            log.error("findAllReview", e);
            throw e;
        }
        return reviews;
    }

    @PostMapping("/create") // RequestMapping 뒤에 붙는  url
    public ResponseEntity<Object> createReview(@RequestBody ReviewSaveRequestDto requestDto) {
        log.trace("review: {}", requestDto);
        LongResult lr = null;
        try {
            boolean result = reviewService.save(requestDto);
            if(result) {
                Long r_id = reviewService.findReviewId(requestDto.getP_id());
                lr = new LongResult("createReview",r_id,"SUCCESS");
            } else {
                lr = new LongResult("createReview",-1,"FAIL");
            }
        } catch (RuntimeException e){
            log.error("createReview", e);
            throw e;
        }
        return new ResponseEntity<Object>(lr, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateReview(@PathVariable Long id, @RequestBody ReviewModifyRequestDto requestDto) {
        log.trace("review: {}", requestDto);
        LongResult lr = null;
        try {
            boolean result = reviewService.modify(id, requestDto);
            if(result) {
//                Long r_id = reviewService.findReviewId(id);
                lr = new LongResult("updateReview", id, "SUCCESS");
            } else {
                lr = new LongResult("updateReview", -1, "FAIL");
            }
        } catch (RuntimeException e) {
            log.error("updateReview", e);
            throw e;
        }
        return new ResponseEntity<Object> (lr, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteReview(@RequestBody ReviewSaveRequestDto requestDto) {
        log.trace("review: {}", requestDto);
        Long r_id = reviewService.findReviewId((requestDto.getP_id()));
        BoolResult br = null;
        try {
            boolean result = reviewService.remove(requestDto);
            if(result) {
                br = new BoolResult(true,"deleteReview", "SUCCESS");
            } else {
                br = new BoolResult(false, "deleteReview", "FAIL");
            }
        } catch (RuntimeException e) {
            log.error("deleteReview", e);
            throw e;
        }
        return new ResponseEntity<Object>(br, HttpStatus.OK);
    }
}
