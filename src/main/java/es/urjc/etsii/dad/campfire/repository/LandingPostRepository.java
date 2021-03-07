package es.urjc.etsii.dad.campfire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.etsii.dad.campfire.model.LandingPost;

public interface LandingPostRepository extends JpaRepository<LandingPost, Long> {

    @Query(value = "SELECT * FROM LandingPost ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<LandingPost> findRandomLandingPost();

}
