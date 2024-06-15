package com.dauphine.event_manager_backend.repository;

import com.dauphine.event_manager_backend.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, UUID> {
}
