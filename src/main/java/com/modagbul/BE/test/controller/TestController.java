package com.modagbul.BE.test.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("test")
    public ResponseEntity<t> testApi() {
        return new ResponseEntity( new t("손현석", 26, "숭실"), HttpStatus.OK);
    }


    @AllArgsConstructor
    @Data
    static class t {
        private String name;
        private int age;
        private String univ;
    }

}