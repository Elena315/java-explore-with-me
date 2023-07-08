package ru.practicum.ewn.service.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewn.service.category.dto.CategoryDto;
import ru.practicum.ewn.service.category.dto.CategoryDtoCreate;
import ru.practicum.ewn.service.category.service.CategoryService;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Slf4j
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDtoCreate categoryDto) {
        log.info("Создана категория с именем = {}", categoryDto.getName());
        return categoryService.createCategory(categoryDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDtoCreate categoryDto,
                                      @PathVariable Long id) {
        log.info("Обновлена категория с именем = {}", categoryDto.getName());
        return categoryService.updateCategory(categoryDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        log.info("Удалена категория с id = {}", id);
        categoryService.deleteCategory(id);
    }

    @GetMapping("/categories/{categoryId}")
    public CategoryDto getCategory(@PathVariable Long id) {
        log.info("Получена категория с id = {}", id);
        return categoryService.getById(id);
    }
}
