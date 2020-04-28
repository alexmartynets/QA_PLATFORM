package com.javamentor.qa.platform.webapp.configs;

import com.javamentor.qa.platform.dao.impl.model.AbstractDAOImpl;
import com.javamentor.qa.platform.models.entity.User;
import com.javamentor.qa.platform.service.impl.UserServisTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.javamentor.qa.platform")
@EntityScan("com.javamentor.qa.platform.models.entity")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        UserServisTest userServisTest = new UserServisTest();
        AbstractDAOImpl<User, Long> dao = new AbstractDAOImpl<>();
        userServisTest.setDao(dao);
        userServisTest.addUser();
    }

}