package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RegistrationService;

import javax.validation.Valid;


@Controller
@RequestMapping()
public class AuthController {
    private final RegistrationService registrationService;

    @Autowired
    public AuthController( RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @GetMapping("/welcome")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "/welcome";
    }
    @PostMapping("/welcome")
    public String PerformRegistration(@ModelAttribute("user") @Valid User user) {
        registrationService.register(user);
        return "redirect:/welcome";
    }
}