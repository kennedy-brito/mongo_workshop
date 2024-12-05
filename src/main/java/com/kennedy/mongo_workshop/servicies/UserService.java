package com.kennedy.mongo_workshop.servicies;

import com.kennedy.mongo_workshop.domain.User;
import com.kennedy.mongo_workshop.dto.UserDTO;
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

    public User insert(User obj){
        return repository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        repository.deleteById(id);

    }

    public User update(User obj){

        User newObj = findById(obj.getId());

        updateData(newObj, obj);

        return repository.save(newObj);
    }


    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }


}
