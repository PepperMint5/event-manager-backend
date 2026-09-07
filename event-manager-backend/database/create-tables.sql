CREATE TABLE category (
                          id UUID PRIMARY KEY,
                          name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_data (
                           id UUID PRIMARY KEY,
                           username VARCHAR(100) NOT NULL UNIQUE,
                           password VARCHAR(255) NOT NULL
);

CREATE TABLE event (
                       id UUID PRIMARY KEY,
                       title VARCHAR(255),
                       city VARCHAR(255),
                       address VARCHAR(255),
                       date DATE,
                       time TIME,
                       description VARCHAR(255),
                       last_updated DATE,
                       category_id UUID,
                       owner_id UUID,

                       CONSTRAINT fk_event_category
                           FOREIGN KEY (category_id)
                               REFERENCES category(id),

                       CONSTRAINT fk_event_owner
                           FOREIGN KEY (owner_id)
                               REFERENCES user_data(id)
                               ON DELETE CASCADE
);

CREATE TABLE friendship (
                            user_id1 UUID NOT NULL,
                            user_id2 UUID NOT NULL,

                            PRIMARY KEY (user_id1, user_id2),

                            CONSTRAINT fk_friendship_user1
                                FOREIGN KEY (user_id1)
                                    REFERENCES user_data(id)
                                    ON DELETE CASCADE,

                            CONSTRAINT fk_friendship_user2
                                FOREIGN KEY (user_id2)
                                    REFERENCES user_data(id)
                                    ON DELETE CASCADE
);

CREATE TABLE participation (
                               user_id UUID NOT NULL,
                               event_id UUID NOT NULL,

                               PRIMARY KEY (user_id, event_id),

                               CONSTRAINT fk_participation_user
                                   FOREIGN KEY (user_id)
                                       REFERENCES user_data(id)
                                       ON DELETE CASCADE,

                               CONSTRAINT fk_participation_event
                                   FOREIGN KEY (event_id)
                                       REFERENCES event(id)
                                       ON DELETE CASCADE
);

CREATE TABLE review (
                        id UUID PRIMARY KEY,
                        event_id UUID,
                        user_id UUID,
                        comment VARCHAR(255),
                        grade INTEGER,
                        date_review DATE,

                        CONSTRAINT fk_review_event
                            FOREIGN KEY (event_id)
                                REFERENCES event(id)
                                ON DELETE CASCADE,

                        CONSTRAINT fk_review_user
                            FOREIGN KEY (user_id)
                                REFERENCES user_data(id)
                                ON DELETE CASCADE
);