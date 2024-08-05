package com.example.movie_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "movie")
@Getter
@Setter
@Entity
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return videoUrl;
    }

    public String setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
        return trailerUrl;
    }

    @Unique
    @Column(name = "name")
    private String nameMovie;

    @Column(name = "poster_url") // trong database thì gạch chân để phần biệt với lại quy tắc chuẩn của database
    private String posterUrl; // images/hinh.png

    @Column(name = "vi_title")
    private String viTitle;

    @Column(name = "en_title")
    private String enTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "video_url", nullable = true)
    private String videoUrl;

    @Column(name = "trailer_url")
    private String trailerUrl;

    @Column(name = "country")
    private String country;

    @Column(name = "year")
    private Long year;

//    private Boolean status ;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genres_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Genre> genres = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_comment",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "movie_evaluation",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "evaluation_id")
    )
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Set<Evaluation> evaluations = new HashSet<>();


    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"movie"})
    private Set<Episode> episodes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Category category;

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }


    public void addCategory(Genre genre) {
        this.genres.add(genre);
    }

    public void addEvaluation(Evaluation evaluation) {
        this.evaluations.add(evaluation);
    }

    // cái movieID em chưa có nhập nên nó ko cso dữ leieuj á a, k cần
    public Movie addEpisode(Episode episode) {
        episode.setMovie(this);
        this.episodes.add(episode);
        return this;
    }

    public Movie setEpisodes(Set<Episode> episodes) {
        episodes.stream().forEach(episode -> {
            episode.setMovie(this);
        });
        this.episodes = episodes;
        return this;
    }
}
