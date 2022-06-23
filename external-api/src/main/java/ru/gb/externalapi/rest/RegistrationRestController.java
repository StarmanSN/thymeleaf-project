package ru.gb.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.gbapimay.security.AuthenticationUserDto;
import ru.gb.gbapimay.security.UserDto;
import ru.gb.gbapimay.security.UserGateway;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/authentication")
public class RegistrationRestController {

    private final UserGateway userGateway;

    @GetMapping()
    public String getRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration-form";
    }
}
