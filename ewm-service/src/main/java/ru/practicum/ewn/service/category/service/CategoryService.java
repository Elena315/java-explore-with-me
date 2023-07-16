package ru.practicum.ewn.service.category.service;

import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewn.service.category.dto.CategoryDto;
import ru.practicum.ewn.service.category.dto.CategoryDtoCreate;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDtoCreate categoryDtoCreate);

    CategoryDto updateCategory(CategoryDtoCreate categoryDtoCreate, Long categoryId);

    void deleteCategory(Long categoryId);

    @Transactional(readOnly = true)
    CategoryDto getById(Long id);

    @Transactional(readOnly = true)
    List<CategoryDto> findCategories(Integer from, Integer size);
}
