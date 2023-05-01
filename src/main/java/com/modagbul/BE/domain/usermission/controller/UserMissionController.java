package com.modagbul.BE.domain.usermission.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission Submit API")
@RequestMapping("/api/v1/{teamId}/missions")
public class UserMissionController {

}
