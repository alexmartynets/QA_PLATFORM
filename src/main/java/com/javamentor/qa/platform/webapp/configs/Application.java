package com.javamentor.qa.platform.webapp.configs;

import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.User;
import com.javamentor.qa.platform.service.impl.UserServisTest;
import com.javamentor.qa.platform.webapp.configs.initializer.TestDataInitialiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("com.javamentor.qa.platform")
@EntityScan("com.javamentor.qa.platform.models.entity")
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
//    @Bean(initMethod = "init")
//
//    public TestDataInitialiser initDataTest(){
//        return new TestDataInitialiser();
//    }






}