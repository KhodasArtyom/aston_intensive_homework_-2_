CREATE TABLE banks
(
    id_bank  SERIAL PRIMARY KEY NOT NULL,
    name     VARCHAR(35)        NOT NULL,
    location VARCHAR(50)        NOT NULL
);

CREATE TABLE clients
(
    id_client  SERIAL PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name  TEXT NOT NULL,
    fk_bank_id INTEGER REFERENCES banks (id_bank) ON DELETE CASCADE
);

