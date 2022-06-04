package ru.gb.thymeleafproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.thymeleafproject.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

//    Cart save(Cart cart);
}
