CREATE EXTENSION if not exists "uuid-ossp";
CREATE TABLE storage_sites
(
    id           UUID primary key not null default uuid_generate_v4(),
    url          VARCHAR(256)     not null,
    status       VARCHAR(128)     not null,
    request_time TIMESTAMP        not null
);