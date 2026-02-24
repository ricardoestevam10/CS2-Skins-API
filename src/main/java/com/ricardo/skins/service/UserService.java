package com.ricardo.skins.service;

import com.ricardo.skins.models.Users;
import com.ricardo.skins.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> findAll(){
        return usersRepository.findAll();
    }

    public Users findById(Long id){
        Optional<Users> optionalUsers = usersRepository.findById(id);
        return optionalUsers.orElse(null);
    }

    public void delete(Long id){
        Users user = new Users();
        user.setId(id);
        usersRepository.delete(user);
    }

    public Users save(Users user){
        return usersRepository.save(user);
    }

    public Users update(Long id, Users user){
        Users userFound = findById(id);
        if(userFound != null){
           return usersRepository.save(user);
        }else{
        return user;
        }
    }
}


