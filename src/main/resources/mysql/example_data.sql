INSERT INTO location (address, max_capacity)
VALUES ('Koszykowa 86, Warszawa', 1000);

INSERT INTO location (address, max_capacity)
VALUES ('Mroczna 666, Hel', 666);

INSERT INTO location (address, max_capacity)
VALUES ('Sloneczna 3, Warszawa', 300);

INSERT INTO location (address, max_capacity)
VALUES ('Mokra 1, Gdansk', 2500);

INSERT INTO location (address, max_capacity)
VALUES ('Bananowa 30, Krakow', 400);

INSERT INTO employee (birth_date, employment_from, salary, education, name, surname, is_student, dtype)
VALUES
    ('1990-02-14', '2015-06-10', 4000, 'HIGHER', 'Lionel', 'Messi', 0, 'Manager'),
    ('1992-05-07', '2019-09-25', 6000, 'HIGHER', 'Cristiano', 'Ronaldo', 0, 'Manager'),
    ('1989-08-22', '2016-11-18', 4600, 'HIGHER', 'Neymar', 'Jr.', 0, 'Manager'),
    ('1995-01-12', '2021-02-05', 5000, 'HIGHER', 'Kylian', 'Mbappé', 0, 'Manager'),
    ('1993-04-28', '2017-07-22', 4400, 'HIGHER', 'Mohamed', 'Salah', 0, 'Manager'),
    ('1991-09-30', '2018-03-15', 4000, 'HIGHER', 'Robert', 'Lewandowski', 0, 'Manager'),
    ('1994-12-03', '2020-10-12', 4300, 'HIGHER', 'Luka', 'Modrić', 0, 'Manager'),
    ('1988-07-18', '2014-05-20', 4700, 'HIGHER', 'Virgil', 'van Dijk', 0, 'Manager'),
    ('1996-03-25', '2022-01-08', 5100, 'HIGHER', 'Kevin', 'De Bruyne', 0, 'Manager'),
    ('1997-06-09', '2019-11-28', 4500, 'HIGHER', 'Harry', 'Kane', 0, 'Manager'),
    ('1994-02-22', '2020-07-01', 3300, 'SECONDARY', 'Luis', 'Suárez', 1, 'Cashier'),
    ('1994-06-29', '2022-03-01', 4200, 'HIGHER', 'Kevin', 'Durant', 0, 'Conservator'),
    ('1990-05-15', '2022-01-01', 5500, 'HIGHER', 'LeBron', 'James', 0, 'Conservator'),
    ('1988-08-24', '2021-11-15', 3200, 'SECONDARY', 'Stephen', 'Curry', 0, 'Cashier'),
    ('1996-07-17', '2021-09-05', 3000, 'SECONDARY', 'Antoine', 'Griezmann', 1, 'Cashier');


INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('Underworld Journey', 1, 1, '2023-06-15', '2023-08-31', 'Discover the mesmerizing beauty of the underwater world with its vibrant marine life and stunning coral reefs. Dive into an immersive experience that showcases the wonders of our oceans. Explore the deep sea creatures, encounter magnificent species, and learn about the delicate balance of marine ecosystems. This exhibition will transport you to an enchanting realm beneath the waves.', 15);

INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('Artistic Expressions', 2, 2, '2023-07-10', '2023-08-15', 'Immerse yourself in a world of artistic expressions that push the boundaries of creativity. Experience a diverse range of art forms, including paintings, sculptures, installations, and digital art. Marvel at the ingenuity and skill of renowned artists, and explore the depths of human imagination through thought-provoking and visually stunning exhibits. This exhibition will inspire and captivate art enthusiasts of all ages.', 12);

INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('Nature Serenity', 3, 3, '2023-07-20', '2023-09-05', 'Indulge in the tranquility of natures serenity as you explore breathtaking landscapes, serene forests, and majestic mountains. Embark on a journey that reconnects you with the beauty of the natural world. Immerse yourself in lush greenery, witness stunning sunsets, and listen to the calming sounds of nature. This exhibition offers a sanctuary for relaxation, inspiration, and appreciation of Earths wonders.', 18);

INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('History Unveiled', 4, 4, '2023-08-01', NULL, 'Uncover the mysteries of the past as you delve into the captivating world of history. From ancient civilizations to modern revolutions, witness the stories that shaped our world. Immerse yourself in interactive exhibits, rare artifacts, and multimedia presentations that bring history to life. Whether you are a history enthusiast or a curious learner, this exhibition promises an engaging and educational experience.', 14);

INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('Dreamscapes', 5, 1, '2023-08-10', '2023-09-30', 'Step into a realm of dreams and imagination where reality meets fantasy. Experience surreal landscapes, magical creatures, and enchanting adventures that will transport you to another world. Marvel at larger-than-life installations, interactive displays, and optical illusions that defy logic and spark the imagination. This exhibition invites you to embrace the power of dreams and explore the limitless possibilities of the human mind.', 16);

INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('Innovations Expo', 6, 2, '2023-09-05', '2023-09-15', 'Explore the cutting-edge innovations that are shaping the future. From technological advancements to groundbreaking discoveries, witness the latest breakthroughs in science, engineering, and industry. Interact with futuristic prototypes, discover revolutionary solutions, and engage with visionary thinkers who are driving progress in various fields. This exhibition is a gateway to the exciting world of innovation and a glimpse into what lies ahead.', 20);

INSERT INTO exhibition (name, id_exhibition_manager, id_location, start_date, end_date, description, ticket_price)
VALUES ('Cultural Diversity', 7, 4, '2023-09-20', '2023-10-31', 'Celebrate the rich tapestry of cultures from around the world. Immerse yourself in the traditions, customs, music, and art that define different societies and foster a sense of global unity. Experience vibrant performances, explore interactive displays, and engage in cultural exchanges that promote understanding and appreciation. This exhibition invites you to embark on a multicultural journey and embrace the diversity that enriches our world.', 10);


INSERT INTO artist (name, surname, birth_date)
VALUES ('John', 'Doe', '1990-05-15'),
       ('Jane', 'Smith', '1985-08-22'),
       ('Michael', 'Johnson', '1992-11-10'),
       ('Emily', 'Williams', '1998-03-27'),
       ('Robert', 'Brown', '2000-01-15'),
       ('Sarah', 'Davis', '1995-07-18'),
       ('Joe', 'Anderson', '2001-10-10');

INSERT INTO artwork (name, creation_year, id_exhibition)
VALUES ('The Starry Night', 1889, 1),
       ('Mona Lisa', 1503, 1),
       ('The Persistence of Memory', 1931, 1),
       ('The Scream', 1893, 1),
       ('Guernica', 1937, 2),
       ('The Last Supper', 1495, 3),
       ('Girl with a Pearl Earring', 1665, 3),
       ('The Birth of Venus', 1486, 3),
       ('The Great Wave off Kanagawa', 1830, 3),
       ('Les Demoiselles dAvignon', 1907, 1);

INSERT INTO artist_artwork (id_artist, id_artwork)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 3),
       (1, 6),
       (5, 10),
       (5, 9),
       (4, 8),
       (3, 8),
       (7, 7),
       (6, 4),
       (6, 5);

INSERT INTO client (birth_date, name, surname)
VALUES
    ('1995-03-20', 'Adam', 'Nowak'),
    ('1997-06-10', 'Katarzyna', 'Kowalska'),
    ('1999-09-05', 'Piotr', 'Wiśniewski'),
    ('2001-12-15', 'Anna', 'Dąbrowska'),
    ('2003-02-25', 'Mateusz', 'Lewandowski');

INSERT INTO ticket (id, id_client, id_cashier, id_exhibition, ticket_discount)
VALUES
    (1, 1, 11, 1, 'SENIOR'),
    (2, 2, 11, 1, 'STUDENT'),
    (3, 3, 14, 2, 'STUDENT'),
    (4, 1, 14, 3, 'STUDENT'),
    (5, 3, 14, 3, 'DISABILITY'),
    (6, 4, 15, 4, 'STUDENT'),
    (7, 2, 15, 4, 'STUDENT'),
    (8, 1, 11, 5, 'STUDENT');

INSERT INTO user_account(email, password)
VALUES
    ('adamn@mail.com', '$2a$10$r9oXlhw8oUTFUXWfiAK.h.7Ce0puIow/rS6FzYZNsZzUkPLnv21MO'),
    ('piotrw@mail.com', '$2a$10$Qojgyq9QCprQ2FbYe1n7K.DTa13lPmax9fvvs1JP11qSeQLNnQdRi');

UPDATE client SET id_account = 1 WHERE id = 1;

INSERT INTO pass (creation_date, end_date, serial_number)
VALUES
    ('2023-01-01', null, 1001),
    ('2023-03-10', '2024-03-10', 1003),
    ('2023-04-20', '2023-06-30', 1004);

UPDATE client SET id_pass = 1001 WHERE id = 1;
UPDATE client SET id_pass = 1003 WHERE id = 3;
UPDATE client SET id_pass = 1004 WHERE id = 4;

INSERT INTO exhibition_pass
VALUES
    (1, 1001),
    (1, 1003),
    (1, 1004),
    (2, 1004),
    (3, 1004);

