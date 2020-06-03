package com.noyak.yakdo.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // @interface : 어노테이션 클래스로 지정 : LoginUser라는 이름을 가진 어노테이션 생성

}
