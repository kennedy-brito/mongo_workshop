package com.kennedy.mongo_workshop.resources;

import com.kennedy.mongo_workshop.domain.Post;
import com.kennedy.mongo_workshop.domain.User;
import com.kennedy.mongo_workshop.dto.UserDTO;
import com.kennedy.mongo_workshop.servicies.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.annotation.Repeatable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){

        List<User> list = userService.findAll();
        List<UserDTO> listDto = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
        User user = userService.fromDTO(userDto);

        user = userService.insert(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDto, @PathVariable String id){
        User user = userService.fromDTO(userDto);
        user.setId(id);
        user = userService.update(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }
}
