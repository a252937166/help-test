package com.ouyanglol.helptest;

import com.ouyanglol.helptest.annotation.EnableMock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMock
public class HelpTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpTestApplication.class, args);
    }

}
