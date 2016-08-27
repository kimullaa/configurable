package com.example;

import com.example.model.BookMark;
import com.example.repositories.BookMarkRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.web.client.RestTemplate;

@EnableSpringConfigured
@SpringBootApplication
public class AspectApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().web(false).build(args).run(AspectApplication.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    BookMarkRepository bookMarkRepository;

    @Bean
    public CommandLineRunner save() {
        return args -> {
            System.out.println("-----------save-----------");
            bookMarkRepository.save(new BookMark("http://google.com"));
            bookMarkRepository.save(new BookMark("http://google.come"));
            System.out.println("--------execute--------");
            bookMarkRepository.findAll().forEach(bookMark -> {
                System.out.println(bookMark.getLink() + " isExist:  " + bookMark.isExist());
            });
        };

    }
}
