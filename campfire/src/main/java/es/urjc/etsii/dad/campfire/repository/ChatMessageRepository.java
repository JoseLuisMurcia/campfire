package es.urjc.etsii.dad.campfire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.etsii.dad.campfire.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}