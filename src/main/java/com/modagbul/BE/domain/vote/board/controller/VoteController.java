//package com.modagbul.BE.domain.vote.board.controller;
//
//import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
//import com.modagbul.BE.domain.vote.board.dto.VoteDto;
//import com.modagbul.BE.domain.vote.board.dto.VoteDto.*;
//import com.modagbul.BE.domain.vote.board.service.VoteService;
//import com.modagbul.BE.global.dto.ResponseDto;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//import java.util.List;
//
//import static com.modagbul.BE.domain.notice.board.constant.NoticeConstant.ENoticeResponseMessage.GET_NOTICE_UNREAD_SUCCESS;
//import static com.modagbul.BE.domain.vote.board.constant.VoteConstant.EVoteResponseMessage.*;
//
//@RestController
//@RequiredArgsConstructor
//@Api(tags = "Vote API")
//@RequestMapping("/api/v1/{teamId}/vote")
//public class VoteController {
//    private final VoteService voteService;
//
//    @ApiOperation(value = "투표 생성", notes = "투표를 생성합니다.")
//    @PostMapping
//    public ResponseEntity<ResponseDto<CreateVoteResponse>> createVote(@PathVariable Long teamId, @Valid @RequestBody CreateVoteRequest createVoteRequest) {
//        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_VOTE_SUCCESS.getMessage(), voteService.createVote(teamId,createVoteRequest)));
//    } //create
//
//    @ApiOperation(value = "투표하기", notes = "투표를 합니다.")
//    @PutMapping("/{voteId}")
//    public ResponseEntity<ResponseDto<GetVoteDetailsResponse>> doVote(@PathVariable Long teamId, @PathVariable Long voteId, @Valid @RequestBody DoVoteRequest doVoteRequest) {
//        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DO_VOTE_SUCCESS.getMessage(), voteService.doVote(teamId, voteId,doVoteRequest)));
//    } //manage
//
//    @ApiOperation(value = "투표결과 조회", notes = "투표 결과를 조회합니다.")
//    @GetMapping("/{voteId}")
//    public ResponseEntity<ResponseDto<GetVoteDetailsResponse>> getVoteDetail(@PathVariable Long teamId, @PathVariable Long voteId) {
//        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_DETAIL_SUCCESS.getMessage(), voteService.getVoteDetail(teamId, voteId)));
//    } //info
//
//    @ApiOperation(value = "투표 종료", notes = "투표를 삭제합니다.")
//    @DeleteMapping("/{voteId}")
//    public ResponseEntity<ResponseDto> closeVote(@PathVariable Long teamId, @PathVariable Long voteId) {
//        this.voteService.closeVote(teamId, voteId);
//        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CLOSE_VOTE_SUCCESS.getMessage()));
//    } //manage
//
//    @ApiOperation(value="투표 전체 조회", notes="투표 블록에서 투표를 전체 조회합니다.")
//    @GetMapping
//    public ResponseEntity<ResponseDto<GetVoteAllResponse>> getVoteAll(@PathVariable Long teamId){
//        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_ALL_SUCCESS.getMessage(), voteService.getVoteAll(teamId)));
//    } //info
//
//    @ApiOperation(value="투표 안읽은 것만 조회", notes="투표에서 안 읽은 것만 최신순으로 조회합니다.")
//    @GetMapping("unread")
//    public ResponseEntity<ResponseDto<List<GetUnReadVoteResponse>>> getUnReadVote(@PathVariable Long teamId){
//        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_UNREAD_SUCCESS.getMessage(),voteService.getUnReadVote(teamId)));
//    } //info
//}
