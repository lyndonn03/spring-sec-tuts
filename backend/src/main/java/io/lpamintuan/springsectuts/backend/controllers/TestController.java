package io.lpamintuan.springsectuts.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    

    @GetMapping("/")
    public String getWelcomePage() {
        return "Welcome ALL";
    }

    @GetMapping("/user")
    public String getUserPage() {
        return "Welcome USER";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "Welcome ADMIN";
    }

}
