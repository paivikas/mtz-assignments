-- Insert dummy data into user_db.user table
INSERT INTO user_db (id, username, password) VALUES
    (uuid_generate_v4(), 'john_doe', 'password123'),
    (uuid_generate_v4(), 'jane_smith', 'securePass!'),
    (uuid_generate_v4(), 'alice_johnson', 'Alice2024'),
    (uuid_generate_v4(), 'bob_brown', 'Bob@1234');

-- Insert dummy data into contact_db.contact table
INSERT INTO contact_db (id, firstname, lastname, email, mobile, deleted, user_id) VALUES
    (uuid_generate_v4(), 'John', 'Doe', 'john.doe@example.com', '123-456-7890', false, (SELECT id FROM user_db WHERE username = 'john_doe')),
    (uuid_generate_v4(), 'Jane', 'Smith', 'jane.smith@example.com', '234-567-8901', false, (SELECT id FROM user_db WHERE username = 'jane_smith')),
    (uuid_generate_v4(), 'Alice', 'Johnson', 'alice.johnson@example.com', '345-678-9012', true, (SELECT id FROM user_db WHERE username = 'alice_johnson')),
    (uuid_generate_v4(), 'Bob', 'Brown', 'bob.brown@example.com', '456-789-0123', false, (SELECT id FROM user_db WHERE username = 'bob_brown'));
