package ru.gb.thymeleafproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafproject.entity.Category;


public interface CategoryDao extends JpaRepository<Category, Long> {

}
