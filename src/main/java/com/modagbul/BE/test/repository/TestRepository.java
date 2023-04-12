package com.modagbul.BE.test.repository;

import com.modagbul.BE.test.entity.TestMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestMember, Long> {
}