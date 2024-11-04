package org.example.springcommerce.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    public RegisterController() {
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
