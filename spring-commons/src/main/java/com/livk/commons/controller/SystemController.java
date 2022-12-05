package com.livk.commons.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * SystemController
 * </p>
 *
 * @author livk
 */
@RestController
@RequiredArgsConstructor
public class SystemController {

    private final ApplicationContext applicationContext;

    @PostMapping("shutdown")
    public void shutdown() {
        System.exit(SpringApplication.exit(applicationContext));
    }

}
