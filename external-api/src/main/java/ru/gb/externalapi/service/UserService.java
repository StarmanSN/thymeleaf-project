package ru.gb.externalapi.service;

import ru.gb.externalapi.entity.security.AccountUser;
import ru.gb.gbapimay.security.UserDto;

import java.util.List;

public interface UserService {

    UserDto register(UserDto userDto);
    UserDto update(UserDto userDto);
    AccountUser findByUsername(String username);
    UserDto findById(Long id);
    List<UserDto> findAll();
    void deleteById(Long id);
}
