package org.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author czz
 */
@SpringBootApplication
public class Demo3Application {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "demo3");
        SpringApplication.run(Demo3Application.class, args);
    }
}
