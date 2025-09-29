package com.shopsphere.controller;
import com.shopsphere.dto.UserDto;
import com.shopsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("userDto") UserDto userDto) {
        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            return "redirect:/register?error&message=Username already exists.";
        }
        try {
            userService.saveUser(userDto);
        } catch (IllegalArgumentException e) {
            return "redirect:/register?error&message=" + e.getMessage();
        }
        return "redirect:/login?success";
    }
}