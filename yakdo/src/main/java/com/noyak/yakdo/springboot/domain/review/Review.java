package com.noyak.yakdo.springboot.domain.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.noyak.yakdo.springboot.domain.BaseTimeEntity;
import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity { // 만들어졌을때, 업데이트 됐을 때 시간 기록
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increse
    private Long r_id;

    @Column(length = 255, nullable = false)
    private String r_writer;

    @Column(columnDefinition = "TEXT")
    private String r_pw;

    @Column(columnDefinition = "TEXT")
    private String r_content;

    @ManyToOne
    @JoinColumn(name = "p_id")
    private Pharmacy p_id;

    @Builder
    public Review(Long r_id, String r_writer, String r_pw, String r_content, Pharmacy p_id) {
        this.r_id = r_id;
        this.r_writer = r_writer;
        this.r_pw = r_pw;
        this.r_content = r_content;
        this.p_id = p_id;
    }

    public void update(String r_content) {
        this.r_content = r_content;
    }
}
