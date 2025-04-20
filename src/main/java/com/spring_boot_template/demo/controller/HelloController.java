package com.spring_boot_template.demo.controller;

import com.spring_boot_template.demo.dto.response.ApiResponse;
import com.spring_boot_template.demo.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    private final HelloService helloService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<String>> hello() {
        return ResponseEntity.ok().body(ApiResponse.<String>builder()
                .data("Hello, World!")
                .build());
    }

    @GetMapping("/protected")
    public String protect() {
        return "Protected api";
    }

    @PostMapping("/transfer")
    public String transfer() {
        log.info("Transfer money");
        return "Transfer money";
    }


}
