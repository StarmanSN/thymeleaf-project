package ru.gb.thymeleafproject.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafproject.entity.security.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {
}
