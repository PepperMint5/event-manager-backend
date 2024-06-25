package com.dauphine.event_manager_backend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
@Table(name = "friendship")
public class Friendship {

    @EmbeddedId
    private FriendshipId id;

    @ManyToOne
    @MapsId("userId1")
    @JoinColumn(name = "user_id1", nullable = false)
    private User user1;

    @ManyToOne
    @MapsId("userId2")
    @JoinColumn(name = "user_id2", nullable = false)
    private User user2;

    public Friendship() {
    }

    public Friendship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.id = new FriendshipId(user1.getId(), user2.getId());
    }


    public FriendshipId getId() {
        return id;
    }

    public void setId(FriendshipId id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
