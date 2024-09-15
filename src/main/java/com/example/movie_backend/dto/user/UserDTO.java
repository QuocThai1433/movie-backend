package com.example.movie_backend.dto.user;

import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private List<String> authorities;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Authority> authoritySet;

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();

    }
}
