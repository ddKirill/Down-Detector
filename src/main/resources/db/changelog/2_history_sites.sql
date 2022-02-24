CREATE TABLE history_sites
(
    id           UUID primary key not null default uuid_generate_v4(),
    url          VARCHAR(256)     not null,
    status       BOOLEAN     not null,
    created_at TIMESTAMP        not null
);