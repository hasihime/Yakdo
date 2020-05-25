package com.noyak.yakdo.springboot.web.dto.pharmacy;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PharmacyResponseDto {
    private int p_id;
    private String p_name;
    private String p_addr;
    private String p_tel;
    private String p_oper;
    private String p_status;
    private String p_special;
    private String p_loc;
    private String p_post;
    private double p_x;
    private double p_y;

    public PharmacyResponseDto(Pharmacy entity) {
        this.p_id = entity.getP_id();
        this.p_name = entity.getP_name();
        this.p_addr = entity.getP_addr();
        this.p_tel = entity.getP_tel();
        this.p_oper = entity.getP_oper();
        this.p_status = entity.getP_status();
        this.p_special = entity.getP_special();
        this.p_loc = entity.getP_loc();
        this.p_post = entity.getP_post();
        this.p_x = entity.getP_x();
        this.p_y = entity.getP_y();
    }

    public Pharmacy toEntity() {
        return Pharmacy.builder().p_id(p_id).p_name(p_name).p_addr(p_addr).p_tel(p_tel).p_oper(p_oper).p_status(p_status).p_loc(p_loc).p_post(p_post).p_x(p_x).p_y(p_y).build();
    }

    @Override
    public String toString() {
        return "PharmacyResponseDto(p_id=" + this.getP_id() + ", p_name=" + this.getP_name() + ", p_addr=" + this.getP_addr() + ", p_tel=" + this.getP_tel() + ", p_oper=" + this.getP_oper() + ", p_status=" + this.getP_status() + ", p_special=" + this.getP_special() + ", p_loc=" + this.getP_loc() + ", p_post=" + this.getP_post() + ", p_x=" + this.getP_x() + ", p_y=" + this.getP_y() + ")";
    }
    
}
