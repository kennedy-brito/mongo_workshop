package com.kennedy.mongo_workshop.servicies;

import com.kennedy.mongo_workshop.domain.Post;
import com.kennedy.mongo_workshop.domain.User;
import com.kennedy.mongo_workshop.dto.UserDTO;
import com.kennedy.mongo_workshop.repository.PostRepository;
import com.kennedy.mongo_workshop.repository.UserRepository;
import com.kennedy.mongo_workshop.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    public Post findById(String id){
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow( () -> new ObjectNotFoundException("Post not found"));
    }

    public List<Post> findByTitle(String text){
        return repository.searchTitle(text);
    }
}
