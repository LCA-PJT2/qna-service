package com.lg2.qna_service.api.open.answer;

import com.lg2.qna_service.domain.dto.csAnswer.CSAnswerRequest;
import com.lg2.qna_service.domain.dto.csAnswer.CSAnswerResponse;
import com.lg2.qna_service.global.code.GeneralSuccessCode;
import com.lg2.qna_service.global.response.CustomResponse;
import com.lg2.qna_service.service.answer.CSAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/answer/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class CSAnswerController {

    @Autowired
    private CSAnswerService csAnswerService;

    // 답변 작성
    @PostMapping
    public ResponseEntity<CustomResponse<CSAnswerResponse.CSAnswerDetailResponse>> createAnswer(
            @RequestBody CSAnswerRequest.CSAnswerCreateRequest request) {

        CSAnswerResponse.CSAnswerDetailResponse response = csAnswerService.createAnswer(request);
        System.out.println("응답 데이터: " + response);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CustomResponse.created(response));
    }

    // 답변 리스트 조회
    @GetMapping
    public ResponseEntity<CustomResponse<Page<CSAnswerResponse.CSAnswerListResponse>>> readAnswerList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) Long questionId
    ) {
        Order order = Order.desc("id");
        Sort sort = Sort.by(order);
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<CSAnswerResponse.CSAnswerListResponse> responseList = csAnswerService.getAnswerList(pageable, questionId);
        return ResponseEntity.ok(CustomResponse.ok(responseList));
    }

    // 특정 답변 조회 (페이지, 특정 질문)
    @GetMapping("/{answerId}")
    public ResponseEntity<CustomResponse<CSAnswerResponse.CSAnswerDetailResponse>> readAnswer(
            @PathVariable Long answerId) {
        CSAnswerResponse.CSAnswerDetailResponse response = csAnswerService.getAnswerDetail(answerId);
        return ResponseEntity.ok(CustomResponse.ok(response));
    }

    // 답변 수정
    @PostMapping("/update/{answerId}")
    public ResponseEntity<CustomResponse<CSAnswerResponse.CSAnswerDetailResponse>> updateAnswer(
            @PathVariable Long answerId,
            @RequestBody CSAnswerRequest.CSAnswerUpdate request) {

        CSAnswerResponse.CSAnswerDetailResponse response = csAnswerService.updateAnswer(answerId, request);
        return ResponseEntity.ok(CustomResponse.ok(response));
    }

    // 답변 삭제
    @PostMapping("/delete/{answerId}")
    public ResponseEntity<CustomResponse<Void>> deleteAnswer(@PathVariable Long answerId) {
        csAnswerService.deleteAnswer(answerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(CustomResponse.success(GeneralSuccessCode._DELETED, null));
    }
}


