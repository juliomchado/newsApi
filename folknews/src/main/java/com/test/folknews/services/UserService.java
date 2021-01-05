package com.test.folknews.services;

import java.util.List;
import java.util.Optional;

import com.test.folknews.exceptions.ObjectNotFoundException;
import com.test.folknews.models.User;
import com.test.folknews.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User newUser) {

        return userRepository.save(newUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException("Objects not found!"));
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
