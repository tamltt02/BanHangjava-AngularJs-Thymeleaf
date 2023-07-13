package com.example.assignmentjava6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui long dang nhap");
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "dang nhap thanh cong");
            return "product/list";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Dang xuat thanh cong");
        return "security/login";
    }
}
