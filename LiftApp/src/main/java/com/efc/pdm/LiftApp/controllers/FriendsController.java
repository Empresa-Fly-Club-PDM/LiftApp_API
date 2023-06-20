package com.efc.pdm.LiftApp.controllers;

import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {
    @Autowired
    FriendsService friendsService;

    @GetMapping("/addFriend/{current}/{friend}")
    public ResponseEntity<?> addUser(@PathVariable Integer current, @PathVariable Integer friend ) throws NullPointerException{
        friendsService.saveFriend(current,friend);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/listFriends/{id}")
    public ResponseEntity<List<User>> getFriends(@PathVariable Integer id) {
        List<User> myFriends = friendsService.getFriends(id);
        return new ResponseEntity<List<User>>(myFriends, HttpStatus.OK);
    }
}
