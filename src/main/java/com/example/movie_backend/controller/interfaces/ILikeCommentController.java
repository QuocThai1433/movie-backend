package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/v1/like_comment")
public interface ILikeCommentController {
    @PostMapping
    ResponseEntity<LikeCommentDTO> create (@RequestBody @Valid  LikeCommentDTO likeComment);

    @PutMapping
    ResponseEntity<LikeCommentDTO> update (@RequestBody @Valid LikeCommentDTO likeComment, Long id);

    @DeleteMapping("{id}")
    boolean delete(@PathVariable("id") Long id);





}
