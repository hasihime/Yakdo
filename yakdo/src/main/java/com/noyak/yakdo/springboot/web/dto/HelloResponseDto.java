package com.noyak.yakdo.springboot.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor // 얘가 제대로 동작을 안한다는거 같은데
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
