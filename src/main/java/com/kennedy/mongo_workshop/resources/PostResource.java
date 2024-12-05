package com.kennedy.mongo_workshop.resources;

import com.kennedy.mongo_workshop.domain.Post;
import com.kennedy.mongo_workshop.domain.User;
import com.kennedy.mongo_workshop.dto.UserDTO;
import com.kennedy.mongo_workshop.servicies.PostService;
import com.kennedy.mongo_workshop.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = postService.findById(id);

        return ResponseEntity.ok().body(post);
    }

}
