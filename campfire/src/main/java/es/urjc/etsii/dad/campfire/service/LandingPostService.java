package es.urjc.etsii.dad.campfire.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.etsii.dad.campfire.entity.LandingPost;
import es.urjc.etsii.dad.campfire.entity.User;
import es.urjc.etsii.dad.campfire.repository.LandingPostRepository;
import es.urjc.etsii.dad.campfire.repository.UserRepository;

@Service
public class LandingPostService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LandingPostRepository landingPostRepo;

    public void storeLandingPost(LandingPost landingPost) {
        landingPostRepo.save(landingPost);
    }

    public void storeLandingPost(String post, boolean isAnonymous, String user) {
        User owner = userRepo.findByUsername(user).orElseThrow();
        storeLandingPost(new LandingPost(post, isAnonymous, owner));
    }

    public LandingPost getRandomLandingPost() {
        Optional<LandingPost> landingPost = landingPostRepo.getRandomLandingPost();
        if (landingPost.isPresent()) {
            return landingPost.get();
        } else {
            return new LandingPost("It's empty over here...", true, null);
        }
    }
}
