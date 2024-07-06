package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.controller.request.GetCategoriesFilter;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.category.NameCategoryRequest;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/categories")
public interface ICategoryController {
    @PostMapping
    ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO category);

    @PutMapping("{id}")
    ResponseEntity<CategoryDTO> update(@PathVariable("id") Long id, @RequestBody CategoryDTO category);

    @GetMapping("{id}")
    ResponseEntity<CategoryDTO> getById(@PathVariable("id") Long id);

    @GetMapping
    ResponseEntity<List<CategoryDTO>> getList(@ParameterObject GetCategoriesFilter filter);

    @DeleteMapping()
    boolean delete(@RequestParam Long id);
}
