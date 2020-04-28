package com.damonx.controller;

import com.damonx.model.GreetingInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HelloController
{

    private static final String template = "Hello, %s! URL: %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello-world")
    @ResponseBody
    public GreetingInfo sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") final String name)
    {
        return new GreetingInfo(counter.incrementAndGet(), String.format(template, name));
    }

}
