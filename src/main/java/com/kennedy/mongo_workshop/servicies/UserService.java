package com.kennedy.mongo_workshop.servicies;

import com.kennedy.mongo_workshop.domain.User;
import com.kennedy.mongo_workshop.repository.UserRepository;
import com.kennedy.mongo_workshop.servicies.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(String id){
        Optional<User> user = repository.findById(id);
        return user.orElseThrow( () -> new ObjectNotFoundException("User not found"));
    }
}
