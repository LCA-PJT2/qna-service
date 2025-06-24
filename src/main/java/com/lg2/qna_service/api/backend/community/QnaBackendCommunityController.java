package com.lg2.qna_service.api.backend.community;

import com.lg2.qna_service.service.question.CSQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/backend/qna/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class QnaBackendCommunityController {

    @Autowired
    private CSQuestionService csQuestionService;

    @GetMapping("/{id}/exists")
    public boolean existsQna(@PathVariable("id") Long questionId) {
        return csQuestionService.existsCSQuestion(questionId);
    }
}
