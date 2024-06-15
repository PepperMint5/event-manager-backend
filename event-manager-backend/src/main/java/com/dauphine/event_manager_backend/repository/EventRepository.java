package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("""
            SELECT event
            FROM Event event
            WHERE UPPER(event.title) LIKE UPPER(CONCAT('%', :title, '%'))
            """)
    List<Event> getAllLikeTitle(String title);
}

