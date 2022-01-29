CREATE TABLE site_url
(
  id SERIAL primary key not null,
  url VARCHAR(256) not null
);

insert into site_url(url) values ('http://google.com');