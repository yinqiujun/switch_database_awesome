package org.example.demo3.controller;

import org.example.demo3.entity.User;
import org.example.demo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czz
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    public User getUserById(@RequestParam("id") Integer id){
        return userService.getUserById(id);
    }
}
