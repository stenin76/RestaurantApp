package com.softuni.Restaurant.controllers;

import com.softuni.Restaurant.model.dto.ChangeUsernameDto;
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
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @ModelAttribute("changeUsernameDto")
    public ChangeUsernameDto  iniChangeDto() {
        return new ChangeUsernameDto();
    }

    @PostMapping("/changeUserName")
    public String register(@Valid ChangeUsernameDto changeUsernameDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("changeUsernameDto", changeUsernameDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.changeUsernameDto", bindingResult);
            return "redirect:/profile";
        }
        if (!(userService.findByUserName(changeUsernameDto.getNewUsername()) == null)) {
            bindingResult.addError(
                    new FieldError(
                            "changeUsernameDto",
                            "newUsername",
                            "Username is already taken!"));
            redirectAttributes
                    .addFlashAttribute("changeUsernameDto", changeUsernameDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.changeUsernameDto", bindingResult);
            return "redirect:/profile";
        }
        this.userService.changeUsername(changeUsernameDto);

        return "redirect:/index";
    }
}
