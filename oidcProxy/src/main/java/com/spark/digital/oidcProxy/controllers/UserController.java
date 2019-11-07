package com.spark.digital.oidcProxy.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, value = "/user/register")
    public String registerUser(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {

        return "registration Complete";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.PUT, value = "/user/password")
    public String resetPassword( @RequestParam(value = "password") String password) {

        return "Password Reset Complete";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.PATCH, value = "/user/profile")
    public String updateUserProfile( @RequestParam(value = "firstName") String firstName) {

        return "Profile Update Complete";
    }
}
