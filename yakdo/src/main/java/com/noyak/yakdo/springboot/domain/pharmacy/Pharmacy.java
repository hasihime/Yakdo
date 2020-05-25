package com.noyak.yakdo.springboot.domain.pharmacy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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

    @Builder
    public Pharmacy(int p_id, String p_name, String p_addr, String p_tel, String p_oper, String p_status, String p_special, String p_loc, String p_post, double p_x, double p_y) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_addr = p_addr;
        this.p_tel = p_tel;
        this.p_oper = p_oper;
        this.p_status = p_status;
        this.p_special = p_special;
        this.p_loc = p_loc;
        this.p_post = p_post;
        this.p_x = p_x;
        this.p_y =p_y;
    }

}
