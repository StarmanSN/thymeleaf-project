package ru.gb.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.gbapimay.security.AuthenticationUserDto;
import ru.gb.gbapimay.security.UserGateway;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/login")
public class LoginRestController {

    private final UserGateway userGateway;

    @GetMapping()
    public String getLoginForm(Model model) {
        AuthenticationUserDto user = new AuthenticationUserDto();
        model.addAttribute("user", user);
        return "login-form";
    }
}