-- Insert 20 more users
INSERT INTO user_db (username, password) VALUES
    ('user1', 'password1'),
    ('user2', 'password2'),
    ('user3', 'password3'),
    ('user4', 'password4'),
    ('user5', 'password5'),
    ('user6', 'password6'),
    ('user7', 'password7'),
    ('user8', 'password8'),
    ('user9', 'password9'),
    ('user10', 'password10'),
    ('user11', 'password11'),
    ('user12', 'password12'),
    ('user13', 'password13'),
    ('user14', 'password14'),
    ('user15', 'password15'),
    ('user16', 'password16'),
    ('user17', 'password17'),
    ('user18', 'password18'),
    ('user19', 'password19'),
    ('user20', 'password20');

-- Insert contacts for each user
INSERT INTO contact_db (firstname, lastname, email, mobile, deleted, user_id) VALUES
    -- Contacts for user 1
    ('John1', 'Doe1', 'john1.doe@example.com', '1234567890', false, 1),
    ('Jane1', 'Smith1', 'jane1.smith@example.com', '1234567891', false, 1),
    ('Alice1', 'Johnson1', 'alice1.johnson@example.com', '1234567892', true, 1),

    -- Contacts for user 2
    ('John2', 'Doe2', 'john2.doe@example.com', '1234567893', false, 2),
    ('Jane2', 'Smith2', 'jane2.smith@example.com', '1234567894', false, 2),
    ('Alice2', 'Johnson2', 'alice2.johnson@example.com', '1234567895', true, 2),

    -- Contacts for user 3
    ('John3', 'Doe3', 'john3.doe@example.com', '1234567896', false, 3),
    ('Jane3', 'Smith3', 'jane3.smith@example.com', '1234567897', false, 3),
    ('Alice3', 'Johnson3', 'alice3.johnson@example.com', '1234567898', true, 3),

    -- Contacts for user 4
    ('John4', 'Doe4', 'john4.doe@example.com', '1234567899', false, 4),
    ('Jane4', 'Smith4', 'jane4.smith@example.com', '1234567800', false, 4),
    ('Alice4', 'Johnson4', 'alice4.johnson@example.com', '1234567801', true, 4),

    -- Contacts for user 5
    ('John5', 'Doe5', 'john5.doe@example.com', '1234567802', false, 5),
    ('Jane5', 'Smith5', 'jane5.smith@example.com', '1234567803', false, 5),
    ('Alice5', 'Johnson5', 'alice5.johnson@example.com', '1234567804', true, 5),

    -- Contacts for user 6
    ('John6', 'Doe6', 'john6.doe@example.com', '1234567805', false, 6),
    ('Jane6', 'Smith6', 'jane6.smith@example.com', '1234567806', false, 6),
    ('Alice6', 'Johnson6', 'alice6.johnson@example.com', '1234567807', true, 6),

    -- Contacts for user 7
    ('John7', 'Doe7', 'john7.doe@example.com', '1234567808', false, 7),
    ('Jane7', 'Smith7', 'jane7.smith@example.com', '1234567809', false, 7),
    ('Alice7', 'Johnson7', 'alice7.johnson@example.com', '1234567810', true, 7),

    -- Contacts for user 8
    ('John8', 'Doe8', 'john8.doe@example.com', '1234567811', false, 8),
    ('Jane8', 'Smith8', 'jane8.smith@example.com', '1234567812', false, 8),
    ('Alice8', 'Johnson8', 'alice8.johnson@example.com', '1234567813', true, 8),
    -- Contacts for user 9
    ('John9', 'Doe9', 'john9.doe@example.com', '1234567814', false, 9),
    ('Jane9', 'Smith9', 'jane9.smith@example.com', '1234567815', false, 9),
    ('Alice9', 'Johnson9', 'alice9.johnson@example.com', '1234567816', true, 9),

    -- Contacts for user 10
    ('John10', 'Doe10', 'john10.doe@example.com', '1234567817', false, 10),
    ('Jane10', 'Smith10', 'jane10.smith@example.com', '1234567818', false, 10),
    ('Alice10', 'Johnson10', 'alice10.johnson@example.com', '1234567819', true, 10),

    -- Contacts for user 11
    ('John11', 'Doe11', 'john11.doe@example.com', '2345678901', false, 11),
    ('Jane11', 'Smith11', 'jane11.smith@example.com', '2345678902', false, 11),
    ('Alice11', 'Johnson11', 'alice11.johnson@example.com', '2345678903', true, 11),

    -- Contacts for user 12
    ('John12', 'Doe12', 'john12.doe@example.com', '2345678904', false, 12),
    ('Jane12', 'Smith12', 'jane12.smith@example.com', '2345678905', false, 12),
    ('Alice12', 'Johnson12', 'alice12.johnson@example.com', '2345678906', true, 12),

    -- Contacts for user 13
    ('John13', 'Doe13', 'john13.doe@example.com', '2345678907', false, 13),
    ('Jane13', 'Smith13', 'jane13.smith@example.com', '2345678908', false, 13),
    ('Alice13', 'Johnson13', 'alice13.johnson@example.com', '2345678909', true, 13),

    -- Contacts for user 14
    ('John14', 'Doe14', 'john14.doe@example.com', '2345678910', false, 14),
    ('Jane14', 'Smith14', 'jane14.smith@example.com', '2345678911', false, 14),
    ('Alice14', 'Johnson14', 'alice14.johnson@example.com', '2345678912', true, 14),

    -- Contacts for user 15
    ('John15', 'Doe15', 'john15.doe@example.com', '2345678913', false, 15),
    ('Jane15', 'Smith15', 'jane15.smith@example.com', '2345678914', false, 15),
    ('Alice15', 'Johnson15', 'alice15.johnson@example.com', '2345678915', true, 15),

    -- Contacts for user 16
    ('John16', 'Doe16', 'john16.doe@example.com', '2345678916', false, 16),
    ('Jane16', 'Smith16', 'jane16.smith@example.com', '2345678917', false, 16),
    ('Alice16', 'Johnson16', 'alice16.johnson@example.com', '2345678918', true, 16),

    -- Contacts for user 17
    ('John17', 'Doe17', 'john17.doe@example.com', '2345678919', false, 17),
    ('Jane17', 'Smith17', 'jane17.smith@example.com', '2345678920', false, 17),
    ('Alice17', 'Johnson17', 'alice17.johnson@example.com', '2345678921', true, 17),

    -- Contacts for user 18
    ('John18', 'Doe18', 'john18.doe@example.com', '2345678922', false, 18),
    ('Jane18', 'Smith18', 'jane18.smith@example.com', '2345678923', false, 18),
    ('Alice18', 'Johnson18', 'alice18.johnson@example.com', '2345678924', true, 18),

    -- Contacts for user 19
    ('John19', 'Doe19', 'john19.doe@example.com', '2345678925', false, 19),
    ('Jane19', 'Smith19', 'jane19.smith@example.com', '2345678926', false, 19),
    ('Alice19', 'Johnson19', 'alice19.johnson@example.com', '2345678927', true, 19),

    -- Contacts for user 20
    ('John20', 'Doe20', 'john20.doe@example.com', '2345678928', false, 20),
    ('Jane20', 'Smith20', 'jane20.smith@example.com', '2345678929', false, 20),
    ('Alice20', 'Johnson20', 'alice20.johnson@example.com', '2345678930', true, 20);

