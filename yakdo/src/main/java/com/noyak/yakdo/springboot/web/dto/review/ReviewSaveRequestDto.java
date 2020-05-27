package com.noyak.yakdo.springboot.web.dto.review;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import com.noyak.yakdo.springboot.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewSaveRequestDto {
    private String r_writer;
    private String r_pw;
    private String r_content;
    private int p_id;

    @Override
    public String toString() {
        return "ReviewSaveRequestDto{" +
                "r_writer='" + r_writer + '\'' +
                ", r_pw='" + r_pw + '\'' +
                ", r_content='" + r_content + '\'' +
                ", p_id=" + p_id +
                '}';
    }

    @Builder
    public ReviewSaveRequestDto(String r_writer, String r_pw, String r_content, int p_id) {
        this.r_writer = r_writer;
        this.r_pw = r_pw;
        this.r_content = r_content;
        this.p_id = p_id;
    }
}
