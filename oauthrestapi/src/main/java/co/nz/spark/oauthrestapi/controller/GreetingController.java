package co.nz.spark.oauthrestapi.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

import co.nz.spark.oauthrestapi.data.User;
import co.nz.spark.oauthrestapi.model.Greeting;

@RestController
public class GreetingController {

    private static final String template = "Hi there, %s!";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@AuthenticationPrincipal final User user) {
        return new Greeting(counter.incrementAndGet(), String.format(template, user.getName()));
    }
}
