package com.kennedy.mongo_workshop.resources;

import com.kennedy.mongo_workshop.domain.User;
import com.kennedy.mongo_workshop.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){

        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
