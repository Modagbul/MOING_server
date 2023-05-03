package com.modagbul.BE.domain.notice_read.repository;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.notice_read.entity.NoticeRead;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeReadRepository extends JpaRepository<NoticeRead, Long>, NoticeReadRepositoryCustom {
    Optional<NoticeRead> findByUserAndNotice(User user, Notice notice);
}
