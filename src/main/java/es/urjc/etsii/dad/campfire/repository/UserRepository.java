package es.urjc.etsii.dad.campfire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.etsii.dad.campfire.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
