package com.noyak.yakdo.springboot.web;

import com.noyak.yakdo.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin({"*"})
@RestController
public class UserController {
    private final HttpSession httpSession;
//
//    @GetMapping("/login")
//    public User Login() {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//        if(user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//
//        return "index";
//    }
}
