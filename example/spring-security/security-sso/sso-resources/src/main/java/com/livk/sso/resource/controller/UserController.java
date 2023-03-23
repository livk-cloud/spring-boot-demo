package com.livk.sso.resource.controller;

import com.livk.sso.commons.util.SecurityContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * UserController
 * </p>
 *
 * @author livk
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("list")
    public HttpEntity<String> list() {
        log.info("{}", SecurityContextUtils.getUser());
        return ResponseEntity.ok("list");
    }

    @PutMapping("update")
    public HttpEntity<String> update() {
        log.info("{}", SecurityContextUtils.getUser());
        return ResponseEntity.ok("update");
    }

}