package com.example.movie_backend.dto.episode;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // cái này nó sẽ bỏ hết những field null
public class EpisodeDTO {

    private Long id;

    private Long episodeCount;

    private String posterUrl;

    private String videoUrl;

    private String descriptions;

    private Long movieId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private MovieDTO movie;

    private String tempId;
}
