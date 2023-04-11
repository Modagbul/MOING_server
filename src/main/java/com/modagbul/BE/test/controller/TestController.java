package com.modagbul.BE.test.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @GetMapping("test")
    public ResponseEntity<List<t>> testApi() {
        List<t> list = new ArrayList<>();
        list.add(new t("손현석", 26, "숭실"));
        list.add(new t("정승연",24,"숭실"));
        list.add( new t("곽승엽",25,"숭실"));
        return new ResponseEntity( list, HttpStatus.OK);
    }


    @AllArgsConstructor
    @Data
    static class t {
        private String name;
        private int age;
        private String univ;
    }

}