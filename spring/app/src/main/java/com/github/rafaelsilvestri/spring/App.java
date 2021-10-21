/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.rafaelsilvestri.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("\n\n\n================================");
            FooSingleton beanOne = ctx.getBean("fooSingleton", FooSingleton.class);
            System.out.println(beanOne.hello());

            FooSingleton beanTwo = ctx.getBean("fooSingleton", FooSingleton.class);
            System.out.println(beanTwo.hello());
        };
    }
}