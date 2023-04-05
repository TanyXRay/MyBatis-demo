package ru.home.chernyadieva.mybatisdemo;

import org.apache.ibatis.type.MappedTypes;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.home.chernyadieva.mybatisdemo.model.StudentModel;

@MappedTypes(StudentModel.class)
@MapperScan("ru.home.chernyadieva.mybatisdemo.mapper")
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
