package ru.gb.thymeleafproject.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE("Доступно"), DISABLED("Недоступно");

    private final String title;
}
