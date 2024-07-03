package com.dauphine.event_manager_backend.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class FriendshipId implements Serializable {

    private UUID userId1;
    private UUID userId2;

    public FriendshipId() {
    }

    public FriendshipId(UUID userId1, UUID userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public UUID getUserId1() {
        return userId1;
    }

    public void setUserId1(UUID userId1) {
        this.userId1 = userId1;
    }

    public UUID getUserId2() {
        return userId2;
    }

    public void setUserId2(UUID userId2) {
        this.userId2 = userId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipId that = (FriendshipId) o;
        return Objects.equals(userId1, that.userId1) && Objects.equals(userId2, that.userId2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId1, userId2);
    }
}
