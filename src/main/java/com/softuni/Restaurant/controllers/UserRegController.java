package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.dto.UserRegisterDto;
import com.softuni.Restaurant.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserRegController {

    private final UserService userService;

    public UserRegController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDto")
    public UserRegisterDto iniRegisterDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDto userRegisterDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:/register";
        }
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError(
                            "userRegisterDto",
                            "confirmPassword",
                            "Password and Confirm Password must be the same."));
            redirectAttributes
                    .addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:/register";
        }
        if (!(userService.findByUserName(userRegisterDto.getUsername()) == null)) {
            bindingResult.addError(
                    new FieldError(
                            "userRegisterDto",
                            "username",
                            "Username is already taken!"));
            redirectAttributes
                    .addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:/register";
        }
        if (!(userService.findUserByEmail(userRegisterDto.getEmail()) == null)) {
            bindingResult.addError(
                    new FieldError(
                            "userRegisterDto",
                            "email",
                            "Email is already taken!"));
            redirectAttributes
                    .addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);
            return "redirect:/register";
        }
        this.userService.registerUser(userRegisterDto);

        return "redirect:/login";
    }
}
