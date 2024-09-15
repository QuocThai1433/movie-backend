package com.example.movie_backend.dto.comment;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CommentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String content;

    private Long idUser;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDTO user;

    private Long idMovie;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date currentDate;

    @JsonIgnore
    private MovieDTO movie;

    private Long totalLikes;

    private CommentDTO parentComment;

    @JsonIgnore
    private LikeComment likeComment ;

    private List<CommentDTO> subordinates = new ArrayList<>();

    public CommentDTO(Comment comment, Long totalLikes) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.idUser = comment.getUser() != null ? comment.getUser().getId() : null;
        this.idMovie = comment.getMovie() != null ? comment.getMovie().getId() : null;
        this.totalLikes = totalLikes;
        if (comment.getUser() != null) {
            this.user = new UserDTO(comment.getUser());
        }
        this.currentDate = comment.getCurrentDate();
        if (comment.getSubordinates() != null) {
            this.subordinates = new ArrayList<>();
            for (Comment sub : comment.getSubordinates()) {
                this.subordinates.add(new CommentDTO(sub, 0L));
            }
        }
    }
}
