package es.urjc.etsii.dad.campfire.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.dad.campfire.model.User;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

@Service
public class FriendListService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getFriendList(String username) {
        User user = userRepo.findByUsername(username).get();
        return user.getFriends();
    }

    public List<User> getFriendRequests(String username) {
        User user = userRepo.findByUsername(username).get();
        return user.getFriendRequests();
    }

    public FriendRequestResponse sendFriendRequest(String from, String to) {
        User fromUser = userRepo.findByUsername(from).get();
        Optional<User> toUser = userRepo.findByUsername(to);

        if (!toUser.isPresent()) {
            return FriendRequestResponse.USER_NOT_FOUND;
        } else {
            User resolvedToUser = toUser.get();

            if (fromUser.getId() == resolvedToUser.getId()) {
                return FriendRequestResponse.SELF_REQUEST;
            }

            if (fromUser.getFriends().contains(resolvedToUser)) {
                return FriendRequestResponse.USER_ALREADY_FRIEND;
            }

            if (resolvedToUser.getFriendRequests().contains(fromUser)) {
                return FriendRequestResponse.PENDING_REQUEST;
            }

            resolvedToUser.getFriendRequests().add(fromUser);
            userRepo.save(resolvedToUser);
            return FriendRequestResponse.SENT;
        }
    }

    public void declineFriendRequest(String friendRequest, String username) {
        User user = userRepo.findByUsername(username).get();
        User fromUser = userRepo.findByUsername(friendRequest).get();
        user.getFriendRequests().remove(fromUser);
        userRepo.save(user);
    }

    public void acceptFriendRequest(String friendRequest, String username) {
        User user = userRepo.findByUsername(username).get();
        User fromUser = userRepo.findByUsername(friendRequest).get();
        user.getFriendRequests().remove(fromUser);
        user.getFriends().add(fromUser);
        fromUser.getFriends().add(user);
        userRepo.save(user);
        userRepo.save(fromUser);
    }

    public boolean checkFriendship(String usernameA, String usernameB) {
        User userA = userRepo.findByUsername(usernameA).get();
        User userB = userRepo.findByUsername(usernameB).get();

        System.out.println(userA.getFriends());
        System.out.println(userB.getFriends());

        boolean result = userA.getFriends().contains(userB);
        System.out.println(result);
        return result;
    }
}
