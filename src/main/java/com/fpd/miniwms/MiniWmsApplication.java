package com.fpd.miniwms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MiniWmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniWmsApplication.class, args);
    }

}
