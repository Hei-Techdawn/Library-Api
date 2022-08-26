package com.example.libraryapi.controller;

import com.example.libraryapi.model.MyUser;
import com.example.libraryapi.service.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
@CrossOrigin(origins = "*")
public class MyUserController {
    private MyUserService myUserService;

    @PostMapping(value = "")
    public MyUser createUser(
            @RequestBody MyUser myUser
    ) {
        return myUserService.createUser(myUser);
    }

    @GetMapping(value = "")
    public List<MyUser> getUsers() {
        return myUserService.getAllUsers();
    }

    @GetMapping(value = "/{id}")
    public MyUser getUser(@PathVariable int id) {
        return myUserService.getUserById(id);
    }

    @PutMapping(value = "/{id}")
    public MyUser putUser(@PathVariable int id, @RequestBody MyUser myUser) {
        return myUserService.updateUserById(id, myUser);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        myUserService.deleteUser(id);
    }
}
