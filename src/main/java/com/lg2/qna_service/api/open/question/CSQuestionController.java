package com.lg2.qna_service.api.open.question;

import com.lg2.qna_service.common.dto.ApiResponseDto;
import com.lg2.qna_service.domain.dto.csQuestion.CSQuestionResponse;
import com.lg2.qna_service.service.question.CSQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/question/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CSQuestionController {

    @Autowired
    CSQuestionService csQuestionService;

    @GetMapping
    public ApiResponseDto<Page<CSQuestionResponse>> getCSQuestionList(@RequestParam(required = false) String category,
                                            @RequestParam(defaultValue = "1") int page) {
        Order order = Order.desc("id");
        Sort sort = Sort.by(order);
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<CSQuestionResponse> list = csQuestionService.getCSQuestionList(category, pageable);
        return ApiResponseDto.createOk(list);
    }

    @GetMapping("/{questionId}")
    public ApiResponseDto<CSQuestionResponse> getCSQuestion(@PathVariable Long questionId) {
        CSQuestionResponse question = csQuestionService.getCSQuestion(questionId);

        return ApiResponseDto.createOk(question);
    }

    @GetMapping("/today")
    public ApiResponseDto<CSQuestionResponse> getTodayCSQuestion() {
        CSQuestionResponse question = csQuestionService.getTodayCSQuestion();

        return ApiResponseDto.createOk(question);
    }

    @PostMapping("/delete/{questionId}")
    public ApiResponseDto<String> deleteCSQuestion(@PathVariable Long questionId) {
        csQuestionService.deleteQuestion(questionId);

        return ApiResponseDto.defaultOk();
    }
}
