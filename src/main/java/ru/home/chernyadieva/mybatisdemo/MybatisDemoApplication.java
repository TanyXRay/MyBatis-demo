package ru.home.chernyadieva.mybatisdemo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MybatisDemoApplication {

    @Bean
    public ModelMapper getInstanceModelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
    }
}
