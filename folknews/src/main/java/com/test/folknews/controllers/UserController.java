package com.test.folknews.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.test.folknews.dtos.UserDTO;
import com.test.folknews.models.User;
import com.test.folknews.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {

        User newUserCreated = userService.createUser(newUser);

        return ResponseEntity.ok().body(newUserCreated);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();

        List<UserDTO> userDTO = list.stream().map(x -> new UserDTO(x.getId(), x.getName(), x.getEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(userDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User user = userService.findById(id);

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());

        return ResponseEntity.ok().body(userDTO);

    }

    // @PutMapping("/{id}")
    // public ResponseEntity<UserDTO> updateUser(@RequestBody User newUser, @PathVariable String id) {
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserDTO>> deleteUser(@PathVariable String id) {

        userService.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
