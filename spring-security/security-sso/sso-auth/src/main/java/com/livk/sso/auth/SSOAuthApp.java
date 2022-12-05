package com.livk.sso.auth;

import com.livk.commons.spring.LivkSpring;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * SSOAuthApp
 * </p>
 *
 * @author livk
 */
@SpringBootApplication
public class SSOAuthApp {

    public static void main(String[] args) {
        LivkSpring.run(SSOAuthApp.class, args);
    }
}
