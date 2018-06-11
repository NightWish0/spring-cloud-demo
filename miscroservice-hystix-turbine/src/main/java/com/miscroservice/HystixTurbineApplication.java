package com.miscroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
@EnableEurekaClient
@EnableTurbineStream
public class HystixTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystixTurbineApplication.class, args);
    }
}
