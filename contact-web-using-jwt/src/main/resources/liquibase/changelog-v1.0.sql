-- Create the User table
CREATE TABLE user_db (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Create the Contact table
CREATE TABLE contact_db (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255),
    mobile VARCHAR(255),
    deleted BOOLEAN,
    user_id UUID,
    CONSTRAINT fk_contact_user FOREIGN KEY (user_id) REFERENCES user_db(id)
);
