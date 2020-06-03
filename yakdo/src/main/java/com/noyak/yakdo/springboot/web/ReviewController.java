package com.noyak.yakdo.springboot.web;

import com.noyak.yakdo.springboot.result.LongResult;
import com.noyak.yakdo.springboot.service.ReviewService;
import com.noyak.yakdo.springboot.web.dto.review.ReviewSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/review") // 앞에 공통으로 붙는 url
public class ReviewController {
    private final ReviewService reviewService;

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
}
