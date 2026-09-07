-- Categories

INSERT INTO category (id, name) VALUES
                                    ('00000000-0000-0000-0000-000000000001', 'Arts & Theatre'),
                                    ('00000000-0000-0000-0000-000000000002', 'Business'),
                                    ('00000000-0000-0000-0000-000000000003', 'Charity & Causes'),
                                    ('00000000-0000-0000-0000-000000000004', 'Community'),
                                    ('00000000-0000-0000-0000-000000000005', 'Education'),
                                    ('00000000-0000-0000-0000-000000000006', 'Family & Kids'),
                                    ('00000000-0000-0000-0000-000000000007', 'Fashion'),
                                    ('00000000-0000-0000-0000-000000000008', 'Film & Media'),
                                    ('00000000-0000-0000-0000-000000000009', 'Food & Drink'),
                                    ('00000000-0000-0000-0000-000000000010', 'Government'),
                                    ('00000000-0000-0000-0000-000000000011', 'Health & Wellness'),
                                    ('00000000-0000-0000-0000-000000000012', 'Hobbies'),
                                    ('00000000-0000-0000-0000-000000000013', 'Holiday'),
                                    ('00000000-0000-0000-0000-000000000014', 'Music'),
                                    ('00000000-0000-0000-0000-000000000015', 'Networking'),
                                    ('00000000-0000-0000-0000-000000000016', 'Science'),
                                    ('00000000-0000-0000-0000-000000000017', 'Spirituality'),
                                    ('00000000-0000-0000-0000-000000000018', 'Sports'),
                                    ('00000000-0000-0000-0000-000000000019', 'Technology'),
                                    ('00000000-0000-0000-0000-000000000020', 'Travel & Outdoor');


-- Demo users

INSERT INTO user_data (id, username, password) VALUES
                                                   (
                                                       '10000000-0000-0000-0000-000000000001',
                                                       'alex_demo',
                                                       '$2a$10$IEpSpSVGJyyA4UV8XmsdZOpP2z9r0IehWOyi6nHQFhfTuk3pvq/4u'
                                                   ),
                                                   (
                                                       '10000000-0000-0000-0000-000000000002',
                                                       'sam_demo',
                                                       '$2a$10$IEpSpSVGJyyA4UV8XmsdZOpP2z9r0IehWOyi6nHQFhfTuk3pvq/4u'
                                                   ),
                                                   (
                                                       '10000000-0000-0000-0000-000000000003',
                                                       'charlie_demo',
                                                       '$2a$10$IEpSpSVGJyyA4UV8XmsdZOpP2z9r0IehWOyi6nHQFhfTuk3pvq/4u'
                                                   );

-- Demo events

INSERT INTO event (
    id,
    title,
    city,
    address,
    date,
    time,
    description,
    last_updated,
    category_id,
    owner_id
) VALUES
      (
          '20000000-0000-0000-0000-000000000001',
          'Tech Meetup Toulouse',
          'Toulouse',
          '12 Rue Alsace Lorraine',
          CURRENT_DATE + 14,
          '18:30:00',
          'An evening meetup about web development and technology.',
          CURRENT_DATE,
          '00000000-0000-0000-0000-000000000019',
          '10000000-0000-0000-0000-000000000001'
      ),
      (
          '20000000-0000-0000-0000-000000000002',
          'Indie Music Night',
          'Paris',
          '25 Rue Oberkampf',
          CURRENT_DATE + 30,
          '20:00:00',
          'A night dedicated to emerging independent artists.',
          CURRENT_DATE,
          '00000000-0000-0000-0000-000000000014',
          '10000000-0000-0000-0000-000000000002'
      ),
      (
          '20000000-0000-0000-0000-000000000003',
          'Community Brunch',
          'Lyon',
          '8 Place Bellecour',
          CURRENT_DATE - 20,
          '11:00:00',
          'A relaxed brunch to meet people from the local community.',
          CURRENT_DATE - 20,
          '00000000-0000-0000-0000-000000000004',
          '10000000-0000-0000-0000-000000000003'
      ),
      (
          '20000000-0000-0000-0000-000000000004',
          'Outdoor Discovery Walk',
          'Annecy',
          '1 Quai de la Tournette',
          CURRENT_DATE - 10,
          '09:30:00',
          'A guided outdoor walk around the lake and surrounding trails.',
          CURRENT_DATE - 10,
          '00000000-0000-0000-0000-000000000020',
          '10000000-0000-0000-0000-000000000001'
      );

-- Participations

INSERT INTO participation (user_id, event_id) VALUES
                                                  (
                                                      '10000000-0000-0000-0000-000000000002',
                                                      '20000000-0000-0000-0000-000000000001'
                                                  ),
                                                  (
                                                      '10000000-0000-0000-0000-000000000003',
                                                      '20000000-0000-0000-0000-000000000001'
                                                  ),
                                                  (
                                                      '10000000-0000-0000-0000-000000000001',
                                                      '20000000-0000-0000-0000-000000000003'
                                                  ),
                                                  (
                                                      '10000000-0000-0000-0000-000000000002',
                                                      '20000000-0000-0000-0000-000000000003'
                                                  ),
                                                  (
                                                      '10000000-0000-0000-0000-000000000003',
                                                      '20000000-0000-0000-0000-000000000004'
                                                  );


-- Reviews for past events

INSERT INTO review (
    id,
    event_id,
    user_id,
    comment,
    grade,
    date_review
) VALUES
      (
          '30000000-0000-0000-0000-000000000001',
          '20000000-0000-0000-0000-000000000003',
          '10000000-0000-0000-0000-000000000001',
          'Great atmosphere and a very friendly event.',
          5,
          CURRENT_DATE - 19
      ),
      (
          '30000000-0000-0000-0000-000000000002',
          '20000000-0000-0000-0000-000000000004',
          '10000000-0000-0000-0000-000000000003',
          'Beautiful route and a well organized morning.',
          4,
          CURRENT_DATE - 9
      );