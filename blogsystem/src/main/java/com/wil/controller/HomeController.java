package com.wil.controller;

import com.wil.entity.User;
import com.wil.exception.ServiceException;
import com.wil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by wil on 2018/8/16.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "index";
        } else {
            return "redirect:/article/list";
        }

    }

    @PostMapping("/login")
    public String login(String phone, String password,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        User user = null;
        try {
            user = userService.login(phone, password);
            if(user != null) {
                session.setAttribute("user", user);
                return "redirect:/article/list";
            }
        } catch (ServiceException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(String phone, String password,
                           RedirectAttributes redirectAttributes) {

        try {
            userService.register(phone, password);
            redirectAttributes.addFlashAttribute("message", "注册成功，请登录!");
            return "redirect:/login";
        } catch (ServiceException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
            return "redirect:/register";
        }

    }

    @GetMapping("/forget")
    public String forget() {
        return "forget";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }




}
