package com.noyak.yakdo.springboot.web.dto.review;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long r_id;
    private String r_content;
    private String r_writer;

    public ReviewResponseDto(Review entity) {
        this.r_id = entity.getR_id();
        this.r_content = entity.getR_content();
        this.r_writer = entity.getR_writer();
    }

//    public Pharmacy toEntity() {
//        return Pharmacy.builder()
//                .p_id(p_id)
//                .p_name(p_name)
//                .p_addr(p_addr)
//                .build();
//    }
    @Builder
    public ReviewResponseDto(long r_id, String r_content, String r_writer) {
        this.r_id = r_id;
        this.r_content = r_content;
        this.r_writer = r_writer;
    }

    
}
