package com.github.rebue.scx;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class OapApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OapApplication.class, args);
    }

}