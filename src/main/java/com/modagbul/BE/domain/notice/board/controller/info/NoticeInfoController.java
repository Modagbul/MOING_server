package com.modagbul.BE.domain.notice.board.controller.info;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.service.info.NoticeInfoService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.modagbul.BE.domain.notice.board.constant.NoticeConstant.ENoticeResponseMessage.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice API")
@RequestMapping("/api/v1/{teamId}/notice")
public class NoticeInfoController {

    private final NoticeInfoService noticeInfoService;

    @ApiOperation(value = "공지 상세 조회", notes = "공지를 상세 조회합니다.")
    @GetMapping("/{noticeId}")
    public ResponseEntity<ResponseDto<NoticeDto.GetNoticeDetailsResponse>> getNoticeDetail(@PathVariable Long teamId, @PathVariable Long noticeId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_NOTICE_DETAIL_SUCCESS.getMessage(), this.noticeInfoService.getNoticeDetails(teamId, noticeId)));
    } //info

    @ApiOperation(value="공지 전체 조회", notes="공지 블록에서 공지를 전체 조회합니다.")
    @GetMapping
    public ResponseEntity<ResponseDto<NoticeDto.GetNoticeAllResponse>> getNoticeAll(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_NOTICE_ALL_SUCCESS.getMessage(),this.noticeInfoService.getNoticeAll(teamId)));
    } //info

    @ApiOperation(value="공지 안읽은 것만 조회", notes="공지에서 안 읽은 것만 최신순으로 조회합니다.")
    @GetMapping("unread")
    public ResponseEntity<ResponseDto<List<NoticeDto.GetUnReadNoticeResponse>>> getUnReadNotice(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_NOTICE_UNREAD_SUCCESS.getMessage(),this.noticeInfoService.getUnReadNotice(teamId)));
    } //info

}
