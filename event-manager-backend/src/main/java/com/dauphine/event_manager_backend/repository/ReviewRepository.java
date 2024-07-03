package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("""
                SELECT r
                FROM Review r
                WHERE r.event.id = :id
            """
    )
    List<Review> getByEventId(UUID id);

    @Query("""
                SELECT r
                FROM Review r
                WHERE r.user.id = :id
            """
    )
    List<Review> getEvenByUser(UUID id);

    List<Review> findByUserId(UUID userId);
}
