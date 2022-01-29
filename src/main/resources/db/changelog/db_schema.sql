CREATE EXTENSION if not exists "uuid-ossp";
CREATE TABLE site_url
(
    id  UUID primary key not null default uuid_generate_v4(),
    url VARCHAR(256) not null

);
 insert into site_url(url) values ('http://google.com');