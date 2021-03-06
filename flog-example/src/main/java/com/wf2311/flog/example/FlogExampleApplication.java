package com.wf2311.flog.example;

import com.wf2311.flog.example.entity.User;
import com.wf2311.log.annotation.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FlogExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlogExampleApplication.class, args);
    }


    @PutMapping("/user/{userId}")
    @Log
    public User modify(@PathVariable Long userId, @RequestBody User user) {
        user.setId(userId);
        return user;
    }

}
