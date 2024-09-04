package com.cesar31.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
@Slf4j
public class PublicController {

    @GetMapping("say-hello")
    public String sayHello() {
        log.info("User wants hello!");
        return "Hello from SA-Gateway!";
    }
}
