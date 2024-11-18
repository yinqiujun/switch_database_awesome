package org.example.demo4.controller;

import jakarta.annotation.Resource;
import org.example.demo4.entity.User;
import org.example.demo4.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czz
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/getUserById")
    public User getUserById(@RequestParam("id") Integer id){
        return userService.getUserById(id);
    }
}
