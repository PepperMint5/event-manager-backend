package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;
import com.dauphine.event_manager_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {

    @Query("""
            SELECT p.user
            FROM Participation p
            WHERE p.event.id = :eventId"""
    )
    List<User> findAllUsersByEventId(UUID eventId);

    @Query("""
            SELECT count(p)
            FROM Participation p
            WHERE p.event.id = :eventId"""
    )
    int countNumberOfUsersByEventId(UUID eventId);

    @Query("""
            SELECT COUNT(p) > 0
            FROM Participation p
            WHERE p.user.id = :userId AND p.event.id = :eventId"""
    )
    boolean existsByUserIdAndEventId(UUID userId, UUID eventId);

    @Query("""
            SELECT p.event
            FROM Participation p
            WHERE p.user.id = :userId"""
    )
    List<Event> findAllEventsByUserId(UUID userId);

    @Transactional
    @Modifying
    @Query("""
            DELETE
            FROM Participation p
            WHERE p.user.id = :userId AND p.event.id = :eventId"""
    )
    void deleteByUserIdAndEventId(UUID userId, UUID eventId);


    @Query("""
            SELECT p.event.id FROM Participation p WHERE p.user.id = :userId
            """)
    List<UUID> findAllEventIdsByUserId(UUID userId);


}
