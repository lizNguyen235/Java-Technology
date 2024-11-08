package org.example.springcommerce.Controller;

import org.example.springcommerce.Model.Account;
import org.example.springcommerce.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {
    @Autowired
    RegisterService registerService;
    public RegisterController() {
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register_account")
    public String register(@ModelAttribute Account account, Model model) {
        if(registerService.isRegister(account.getUsername())) {
            model.addAttribute("isRegisterMessage", "Username already exists");
            return "register"; // Trả về view trang đăng ký
        } else {
            registerService.register(account);
            return "redirect:/login"; // Sau khi đăng ký thành công, chuyển hướng đến trang đăng nhập
        }
    }
}
