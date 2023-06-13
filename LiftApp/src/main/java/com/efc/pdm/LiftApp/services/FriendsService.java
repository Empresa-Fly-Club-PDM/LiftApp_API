package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Friends;
import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.FriendsRepository;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {
    @Autowired
    FriendsRepository friendRepository;

    @Autowired
    UserRepository userRepository;

    public void saveFriend(int iduser1, int iduser2) throws NullPointerException{

        Friends friend = new Friends();
        User user1 = userRepository.getReferenceById(iduser1);
        User user2 = userRepository.getReferenceById(iduser2);
        User firstuser = user1;
        User seconduser = user2;
        if(user1.getId()> user2.getId()){
            firstuser = user2;
            seconduser = user1;
        }
        if( !(friendRepository.existsByFirstUserAndSecondUser(firstuser,seconduser)) ){
            friend.setFirstUser(firstuser);
            friend.setSecondUser(seconduser);
            friendRepository.save(friend);
        }
    }

    public List<User> getFriends(Integer userid){
        User currentUser = userRepository.getReferenceById(userid);
        List<Friends> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friends> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();

        /*
            suppose there are 3 users with id 1,2,3.
            if user1 add user2 as friend database record will be first user = user1 second user = user2
            if user3 add user2 as friend database record will be first user = user2 second user = user3
            it is because of lexicographical order
            while calling get friends of user 2 we need to check as a both first user and the second user
         */
        for (Friends friend : friendsByFirstUser) {
            friendUsers.add(userRepository.getReferenceById(friend.getSecondUser().getId()));
        }
        for (Friends friend : friendsBySecondUser) {
            friendUsers.add(userRepository.getReferenceById(friend.getFirstUser().getId()));
        }
        return friendUsers;

    }
}
