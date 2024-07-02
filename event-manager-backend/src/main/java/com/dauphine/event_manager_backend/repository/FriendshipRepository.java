package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Friendship;
import com.dauphine.event_manager_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    boolean existsByUser1AndUser2(User user1, User user2);
}
