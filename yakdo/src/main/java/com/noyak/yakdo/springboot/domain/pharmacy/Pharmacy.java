package com.noyak.yakdo.springboot.domain.pharmacy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@ToString
@Getter  // 객체지향 구현을 위해서
@NoArgsConstructor
@Entity
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int p_id;

    @Column(length = 255, nullable = false)
    private String p_name;

    @Column(length = 255, nullable = false)
    private String p_addr;

    @Column(length = 30)
    private String p_tel;

    @Column(columnDefinition = "TEXT")
    private String p_oper;

    @Column(length = 20)
    private String p_status;

    @Column(columnDefinition = "TEXT")
    private String p_special;

    @Column(columnDefinition = "TEXT")
    private String p_loc;

    @Column(length = 30)
    private String p_post;

    @Column
    private double p_x;

    @Column
    private double p_y;


}
