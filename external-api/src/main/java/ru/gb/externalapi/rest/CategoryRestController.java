package ru.gb.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbapimay.category.api.CategoryGateway;
import ru.gb.gbapimay.category.dto.CategoryDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {

    private final CategoryGateway categoryGateway;

    @GetMapping
    public String getCategoryList(Model model) {
        model.addAttribute("categorys", categoryGateway.getCategoryList());
        return "category-list";
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long id) {
        return categoryGateway.getCategory(id);
    }

    @PostMapping
    public String handlePost(CategoryDto categoryDto) {
        categoryGateway.handlePost(categoryDto);
        return "redirect:/api/v1/category";
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("categoryId") Long id, @Validated @RequestBody CategoryDto categoryDto) {
        return categoryGateway.handleUpdate(id, categoryDto);

    }

    @DeleteMapping("/delete/{categoryId}")
    public String deleteById(@PathVariable("categoryId") Long id) {
        categoryGateway.deleteById(id);
        return "redirect:/category/all";
    }
}
