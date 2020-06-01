package com.noyak.yakdo.springboot.web.dto.pharmacy;

import com.noyak.yakdo.springboot.domain.pharmacy.Pharmacy;
import lombok.Getter;

@Getter
public class PharmacyListResponseDto {
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

    public PharmacyListResponseDto(Pharmacy entity) {
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
}


