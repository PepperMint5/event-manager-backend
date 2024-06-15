package com.dauphine.event_manager_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "event" )
public class Event {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name= "city")
    private String city;

    @Column(name= "address")
    private String address;

    @Column(name= "date")
    private Date date;

    @Column(name= "time")
    private LocalDateTime time;

    @Column(name= "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;


}
