package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("""
            SELECT event
            FROM Event event
            WHERE UPPER(event.title) LIKE UPPER(CONCAT('%', :title, '%'))
            """)
    List<Event> getAllLikeTitle(String title);

    @Query("""
            SELECT event
            FROM Event event
            WHERE (event.user.id = :userId)
            """)
    List<Event> getAllLikeUserId(UUID userId);

    @Query("""
            SELECT event
            FROM Event event
            WHERE (event.category.id = :categoryId)
            """)
    List<Event> getAllLikeCategoryId(UUID categoryId);

    boolean existsByName(String name);
}

