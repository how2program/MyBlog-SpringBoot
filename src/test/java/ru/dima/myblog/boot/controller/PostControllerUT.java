package ru.dima.myblog.boot.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@WebMvcTest
public class PostControllerUT {
}
