package br.com.erudio.rest_with_spring_boot_and_java_erudio.controller;

import br.com.erudio.rest_with_spring_boot_and_java_erudio.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello %s!";
    private final AtomicLong counter = new AtomicLong();

    //localhost:8080/greeting?name=Leandro
    @RequestMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "Universe")
            String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));

    }
}
