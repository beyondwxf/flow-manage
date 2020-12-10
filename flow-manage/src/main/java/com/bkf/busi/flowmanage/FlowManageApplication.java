package com.bkf.busi.flowmanage;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bkf.busi.flowmanage")
public class FlowManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowManageApplication.class, args);
    }

}
