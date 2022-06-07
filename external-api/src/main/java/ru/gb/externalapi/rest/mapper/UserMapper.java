package ru.gb.externalapi.rest.mapper;

import org.mapstruct.Mapper;
import ru.gb.externalapi.entity.security.AccountUser;
import ru.gb.gbapimay.security.UserDto;

@Mapper
public interface UserMapper {
    AccountUser toAccountUser(UserDto userDto);
    UserDto toUserDto(AccountUser accountUser);
}
