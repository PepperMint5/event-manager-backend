package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
}
