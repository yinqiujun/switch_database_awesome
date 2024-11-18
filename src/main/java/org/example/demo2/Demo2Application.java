package org.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author czz
 */
@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "demo2");
        SpringApplication.run(Demo2Application.class, args);
    }
}
