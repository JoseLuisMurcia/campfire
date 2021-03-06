package es.urjc.etsii.dad.campfire.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.etsii.dad.campfire.model.Avatar;
import es.urjc.etsii.dad.campfire.model.User;

public interface AvatarRepository extends JpaRepository<Avatar, Long> 
{
    Optional<Avatar> findByUser(User user);
}
