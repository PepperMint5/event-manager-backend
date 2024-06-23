package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String name);

    boolean existsById(UUID id);

    @Query("SELECT u FROM User u WHERE u.id IN " +
            "(SELECT CASE WHEN f.user1.id = :userId THEN f.user2.id ELSE f.user1.id END " +
            "FROM Friendship f WHERE f.user1.id = :userId OR f.user2.id = :userId)")
    List<User> findFriendsById(@Param("userId") UUID userId);




}
