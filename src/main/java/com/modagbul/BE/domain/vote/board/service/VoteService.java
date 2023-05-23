//package com.modagbul.BE.domain.vote.board.service;
//
//import com.modagbul.BE.domain.vote.board.dto.VoteDto.*;
//import com.modagbul.BE.domain.vote.board.entity.Vote;
//
//import java.util.List;
//
//public interface VoteService {
//    CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest); //create
//    GetVoteDetailsResponse doVote(Long teamId, Long voteId, DoVoteRequest doVoteRequest); //manage
//    GetVoteDetailsResponse getVoteDetail(Long teamId, Long voteId); //info
//    Vote validateVote(Long teamId, Long voteId); //validate
//    void closeVote(Long teamId, Long voteId); //manage
//    GetVoteAllResponse getVoteAll(Long teamId); //info
//    List<GetUnReadVoteResponse> getUnReadVote(Long teamId); //info
//
//}
