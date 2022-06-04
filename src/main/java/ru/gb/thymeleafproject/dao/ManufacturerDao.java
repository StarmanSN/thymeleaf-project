package ru.gb.thymeleafproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafproject.entity.Manufacturer;

import java.util.Optional;

public interface ManufacturerDao extends JpaRepository<Manufacturer, Long> {
    Manufacturer findByNameLike(String name);
    Optional<Manufacturer> findByName(String name);
}
