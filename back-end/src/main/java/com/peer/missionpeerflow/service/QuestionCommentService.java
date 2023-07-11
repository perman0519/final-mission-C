package com.peer.missionpeerflow.service;


import com.peer.missionpeerflow.dto.request.comment.QuestionCommentModifyRequest;
import com.peer.missionpeerflow.dto.request.comment.QuestionCommentRequest;
import com.peer.missionpeerflow.dto.response.QuestionCommentResponse;
import com.peer.missionpeerflow.entity.QuestionComment;
import com.peer.missionpeerflow.repository.QuestionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionCommentService {

    private final QuestionService questionService;
    private final QuestionCommentRepository questionCommentRepository;

    public void create(QuestionCommentRequest questionCommentRequest)
    {
        questionCommentRepository.save(toEntity(questionCommentRequest));
    }

    public QuestionComment toEntity(QuestionCommentRequest questionCommentRequest){
        return QuestionComment.builder()
                .question(questionService.getQuestion(questionCommentRequest.getQuestionId()))
                .nickname(questionCommentRequest.getNickname())
                .password(questionCommentRequest.getPassword())
                .content(questionCommentRequest.getContent())
                .build();
    }

    public QuestionComment getComment(Long commentId){
        return questionCommentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
    }

    public Page<QuestionCommentResponse> getPage(Long questionId, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        Page<QuestionComment> questionCommentPage = questionCommentRepository.findByQuestion(questionService.getQuestion(questionId), pageable);
        return questionCommentPage.map(m -> QuestionCommentResponse.fromQuestion(m));
    }

    public void modify(Long commentId, QuestionCommentModifyRequest questionCommentModifyRequest)
    {
        QuestionComment questionComment = getComment(commentId);
        questionComment.updateQuestionComment(questionCommentModifyRequest.getNickname(), questionCommentModifyRequest.getContent());
        questionCommentRepository.save(questionComment);

    }

    public void delete(Long commentId)
    {
        questionCommentRepository.deleteById(commentId);
    }
}