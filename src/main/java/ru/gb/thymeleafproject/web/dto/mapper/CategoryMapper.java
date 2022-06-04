package ru.gb.thymeleafproject.web.dto.mapper;

import org.mapstruct.Mapper;
import ru.gb.gbapimay.category.dto.CategoryDto;
import ru.gb.thymeleafproject.entity.Category;

@Mapper
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}
