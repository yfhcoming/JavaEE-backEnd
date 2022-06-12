package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sys.mapper")
public class SoundwindBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoundwindBackendApplication.class, args);
    }

}
