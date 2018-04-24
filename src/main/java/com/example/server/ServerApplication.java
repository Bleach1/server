package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.server.*")
public class ServerApplication {

    @Bean
    public SpringContextUtils springUtil2(){return new SpringContextUtils();}



    public static void main(String[] args) {

        SpringApplication.run(ServerApplication.class, args);
        new Server2().getServer();
    }

}

