package com.aab.dev_connect.contoller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Operation(
            summary = "Home Page",
            description = "Welcome to home page"
    )
    @GetMapping
    public String home() {
        return "<h1>Welcome to home page</h1>";
    }

}
