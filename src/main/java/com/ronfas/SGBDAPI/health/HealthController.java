package com.ronfas.SGBDAPI.health;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health")
    @ResponseStatus(HttpStatus.OK)
    public void getHealth() {
    }

    @GetMapping("/teapot")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void getTeaPot() {
    }
}
