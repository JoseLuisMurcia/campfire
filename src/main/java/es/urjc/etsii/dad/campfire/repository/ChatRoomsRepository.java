package es.urjc.etsii.dad.campfire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.etsii.dad.campfire.model.Chat;

//STORES A LIST OF CHATS

public interface ChatRoomsRepository extends JpaRepository<Chat, Long> {

}