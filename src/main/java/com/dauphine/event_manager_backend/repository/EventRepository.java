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
            WHERE (event.owner.id = :userId)
            """)
    List<Event> getAllLikeUserId(UUID userId);

    @Query("""
            SELECT event
            FROM Event event
            WHERE (event.category.id = :categoryId)
            """)
    List<Event> getAllLikeCategoryId(UUID categoryId);

    @Query("""
            SELECT event
            FROM Event event
            WHERE event.date < CURRENT_DATE
            """)
    List<Event> getAllPassedEvents();

    @Query("""
            SELECT event
            FROM Event event
            WHERE event.date > CURRENT_DATE
            """)
    List<Event> getAllUpcomingEvents();

    @Query("""
                SELECT DISTINCT e.city FROM Event e
            """)
    List<String> getAllCitiesWithEvents();

    @Query("""
                SELECT e FROM Event e WHERE e.city = :city
            """)
    List<Event> getAllEventsInCity(String city);

    boolean existsByTitle(String title);

    @Query("""
                SELECT e FROM Event e WHERE e.date >= CURRENT_DATE AND e.id IN (SELECT p.event.id FROM Participation p WHERE p.user.id = :id )
            """)
    List<Event> getAllUpcomingEventsByUserIdParticipation(UUID id);


    @Query("""
                SELECT e FROM Event e WHERE e.date < CURRENT_DATE AND e.id IN (SELECT p.event.id FROM Participation p WHERE p.user.id = :id )
            """)
    List<Event> getAllPastEventsByUserIdParticipation(UUID id);

    List<Event> findByOwnerId(UUID ownerId);
}

