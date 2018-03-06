package com.wf2311.flog.example;

import com.wf2311.log.DefaultLogAspect;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlogExampleApplication {


    public static void main(String[] args) {
        SpringApplication.run(FlogExampleApplication.class, args);
    }
}
