package com.noyak.yakdo.springboot.web.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDeleteRequestDto {
    private Long r_id;
    private String r_pw;

    @Override
    public String toString() {
        return "ReviewSaveRequestDto{" +
                ", r_pw='" + r_pw + '\'' +
                ", r_id=" + r_id +
                '}';
    }

    @Builder
    public ReviewDeleteRequestDto(Long r_id, String r_pw) {
        this.r_id = r_id;
        this.r_pw = r_pw;
    }
}
