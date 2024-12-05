package com.kennedy.mongo_workshop.resources;

import com.kennedy.mongo_workshop.domain.Post;
import com.kennedy.mongo_workshop.resources.util.URL;
import com.kennedy.mongo_workshop.servicies.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);

        List<Post> posts = postService.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }


    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
            ){
        text = URL.decodeParam(text);
        LocalDate min = URL.convertDate(minDate, LocalDate.of(1980, 1, 1));
        LocalDate max = URL.convertDate(maxDate, LocalDate.now());

        List<Post> posts = postService.fullSearch(text, min, max);
        return ResponseEntity.ok().body(posts);
    }


}
