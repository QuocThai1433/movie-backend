package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.comment.CommentDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/comment")
public interface ICommentController {
    @PostMapping("create")
    ResponseEntity<CommentDTO> create(@RequestBody CommentDTO comment);

    @PutMapping("update")
    ResponseEntity<CommentDTO> update(@RequestBody CommentDTO comment, @RequestParam Long movieId);

    @GetMapping("/{id}")
    ResponseEntity<CommentDTO> getById(@PathVariable Long id);

    @DeleteMapping("delete/{id}")
    boolean delete(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<CommentDTO>> getCommentByMovieId(@RequestParam Long commentId);

    @GetMapping("getCommentByUserId")
    ResponseEntity<List<CommentDTO>> getListCommentByMovieIdUserId(@RequestParam Long userId, @RequestParam Long movieId);


}
