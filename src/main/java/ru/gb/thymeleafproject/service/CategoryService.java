package ru.gb.thymeleafproject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbapimay.category.dto.CategoryDto;
import ru.gb.thymeleafproject.dao.CategoryDao;
import ru.gb.thymeleafproject.entity.Category;
import ru.gb.thymeleafproject.service.jms.JmsSenderService;
import ru.gb.thymeleafproject.web.dto.mapper.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryDao categoryDao;
    private final CategoryMapper categoryMapper;
    private final JmsSenderService jmsSenderService;

    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        if (category.getId() != null) {
            categoryDao.findById(categoryDto.getId()).ifPresent(
                    (p) -> category.setVersion(p.getVersion())
            );
        }
        String oldTitle = categoryDto.getTitle();
        String newTitle = categoryDto.getTitle();
        jmsSenderService.sendAndReceiveMessage(oldTitle, newTitle);
        return categoryMapper.toCategoryDto(categoryDao.save(category));
    }


    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        return categoryMapper.toCategoryDto(categoryDao.findById(id).orElse(null));
    }

    public List<CategoryDto> findAll() {
        return categoryDao.findAll().stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        try {
            categoryDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
        }
    }

}
