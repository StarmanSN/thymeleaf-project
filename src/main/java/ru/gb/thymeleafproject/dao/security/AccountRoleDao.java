package ru.gb.thymeleafproject.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafproject.entity.security.AccountRole;

public interface AccountRoleDao extends JpaRepository<AccountRole, Long> {
}
