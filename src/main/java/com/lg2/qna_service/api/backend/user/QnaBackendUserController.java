package com.lg2.qna_service.api.backend.user;

import com.lg2.qna_service.common.dto.ApiResponseDto;
import com.lg2.qna_service.service.answer.CSAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/backend/qna/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class QnaBackendUserController {

    @Autowired
    CSAnswerService csAnswerService;

    @PostMapping("/user/clear/{userId}")
    public ApiResponseDto<String> deleteAnswer(@PathVariable("userId") Long userId) {
        csAnswerService.deleteAnswerByUserId(userId);
        return ApiResponseDto.defaultOk();
    }
}
