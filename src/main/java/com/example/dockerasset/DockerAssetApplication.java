package com.example.dockerasset;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.dockerasset.mapper")
@SpringBootApplication
public class DockerAssetApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerAssetApplication.class, args);
    }
}
