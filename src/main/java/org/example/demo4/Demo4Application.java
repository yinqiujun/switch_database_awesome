package org.example.demo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author czz
 */
@SpringBootApplication
public class Demo4Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "demo4");
        SpringApplication.run(Demo4Application.class, args);
    }
}
