package com.lg2.qna_service.api.open.question;

import com.lg2.qna_service.common.dto.ApiResponseDto;
import com.lg2.qna_service.service.question.AIQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lg2.qna_service.domain.dto.csQuestion.CSQuestionResponse.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/question/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AIQuestionController {

    @Autowired
    private AIQuestionService aiQuestionService;

    @PostMapping("/ai")
    public ApiResponseDto<AIQuestionResponse> generateAIQuestion() {
        AIQuestionResponse response = aiQuestionService.createAIQuestion();

        return ApiResponseDto.createOk(response);
    }
}
