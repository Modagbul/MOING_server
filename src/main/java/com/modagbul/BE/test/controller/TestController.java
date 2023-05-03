package com.modagbul.BE.test.controller;

import com.modagbul.BE.global.dto.ResponseDto;
import com.modagbul.BE.test.entity.TestMember;
import com.modagbul.BE.test.repository.TestRepository;
import com.modagbul.BE.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestRepository testRepository;
    @GetMapping("test")
    public ResponseEntity<List<TestMember>> testApi() {
        List<TestMember> list = new ArrayList<>();
        list.add(new TestMember("손현석", 26, "숭실"));
        list.add(new TestMember("정승연",24,"숭실"));
        list.add( new TestMember("곽승엽",25,"숭실"));
        return new ResponseEntity( list, HttpStatus.OK);
    }
    @PostMapping("test")
    @ResponseBody
    public ResponseEntity<List<TestMember>> testApi(@RequestBody List<TestMember> list) {

        testRepository.saveAll(list);

        return new ResponseEntity( list, HttpStatus.OK);
    }


    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */

    private final TestService testService;
    @ResponseBody
    @GetMapping("oauth/kakao")
    public ResponseEntity<ResponseDto<String>> kakaoCallback(@RequestParam String code) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),"카카오 액세스 토큰 발급", testService.getKakaoAccessToken(code)));
    }



}