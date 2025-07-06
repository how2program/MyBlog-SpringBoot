package ru.dima.myblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "ru.dima.myblog")
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class ServletConfiguration {
}
