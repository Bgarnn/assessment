-- Drop tables if they exist
DROP TABLE IF EXISTS user_ticket CASCADE;
DROP TABLE IF EXISTS lottery CASCADE;

CREATE TABLE lottery (
    ticket_id           VARCHAR(6)  UNIQUE CHECK (ticket_id ~ '^[0-9]{6}$'),
                                    PRIMARY KEY (ticket_id),
    ticket_price        DECIMAL     NOT NULL CHECK (ticket_price >= 0),
    ticket_amount       INT         NOT NULL CHECK (ticket_amount >= 1)
);

CREATE TABLE user_ticket (
    id                  SERIAL      PRIMARY KEY,
    user_id             VARCHAR(10) NOT NULL CHECK (user_id ~ '^[0-9]{10}$'),
    user_ticket_id      VARCHAR(6)  UNIQUE   CHECK (user_ticket_id ~ '^[0-9]{6}$'),
    user_ticket_price   DECIMAL     NOT NULL CHECK (user_ticket_price >= 0),
    user_ticket_amount  INT         NOT NULL CHECK (user_ticket_amount >= 1)
);
