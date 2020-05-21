package com.noyak.yakdo.springboot.web.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
