package io.github.exemple.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginviewController {

    @GetMapping("/login")
    public String paginaLogin(){
        return "login";
    }
}


