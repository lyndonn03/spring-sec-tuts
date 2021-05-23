package io.lpamintuan.springsectuts.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String getStaticPage() {
        return "Welcome all";
    }
    
    @GetMapping("/user")
    public String getUserPage() {
        return "Welcome User";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "Welcome Admin";
    }



}
