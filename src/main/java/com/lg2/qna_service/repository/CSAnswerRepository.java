package com.lg2.qna_service.repository;

import com.lg2.qna_service.domain.CSAnswer;
import com.lg2.qna_service.domain.CSQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CSAnswerRepository extends JpaRepository<CSAnswer, Long> {

    List<CSAnswer> findAllById(Long id);

    Page<CSAnswer> findAllById(Long id, Pageable pageable);

    Page<CSAnswer> findAllByUserIdAndCsQuestion(Long id, CSQuestion csQuestion, Pageable pageable);


    void deleteById(Long id);
    void deleteByUserId(Long userId);
}