package com.github.rebue.scx.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        try {
            System.setProperty("spring.devtools.restart.enabled", "false");
            SpringApplication.run(GatewayApplication.class, args);
        } catch (Throwable e) {
            if (!e.getClass().getName().contains("SilentExitException")) {
                e.printStackTrace();
                throw e;
            }
        }
    }

}
