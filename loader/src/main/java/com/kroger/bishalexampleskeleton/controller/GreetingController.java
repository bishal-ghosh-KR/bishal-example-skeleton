package com.kroger.bishalexampleskeleton.controller;

import com.kroger.bishalexampleskeleton.config.MiscellaneousConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private MiscellaneousConfig miscellaneousConfig;

    @GetMapping("/hello-static")
    public String greetingStaticMessage() {
        return "Hello message(static).";
    }

    @GetMapping("/hello-dynamic")
    public String greetingDynamicMessage() {
        return miscellaneousConfig.getMessage();
    }
}
