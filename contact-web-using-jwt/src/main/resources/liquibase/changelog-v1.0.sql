-- Create the User table with serial ID
CREATE TABLE user_db (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


-- Create the Contact table with serial ID
CREATE TABLE contact_db (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    mobile VARCHAR(255),
    deleted BOOLEAN,
    user_id INTEGER,
    CONSTRAINT fk_contact_user FOREIGN KEY (user_id) REFERENCES user_db(id)
);
