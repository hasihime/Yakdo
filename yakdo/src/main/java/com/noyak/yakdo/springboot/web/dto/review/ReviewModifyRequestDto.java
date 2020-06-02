package com.noyak.yakdo.springboot.web.dto.review;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewModifyRequestDto {
    private Long r_id;
    private String r_pw;
    private String r_content;

    @Override
    public String toString() {
        return "ReviewSaveRequestDto{" +
                "r_pw='" + r_pw + '\'' +
                ", r_content='" + r_content + '\'' +
                '}';
    }

    @Builder
    public ReviewModifyRequestDto(Long r_id, String r_pw, String r_content) {
        this.r_id = r_id;
        this.r_pw = r_pw;
        this.r_content = r_content;
    }
}
