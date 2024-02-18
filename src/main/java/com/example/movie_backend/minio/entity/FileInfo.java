package com.example.movie_backend.minio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String name;
    private Long size;
    private String url;
}
