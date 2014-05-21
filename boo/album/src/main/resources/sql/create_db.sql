CREATE TABLE IF NOT EXISTS users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userName VARCHAR(128) NOT NULL UNIQUE,
  password BLOB NOT NULL,
  fullName VARCHAR(128) NOT NULL);

create unique index userid_index on users (id);
create unique index username_index on users (username);

CREATE TABLE IF NOT EXISTS albums (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  album_name VARCHAR(128) NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id)
      ON UPDATE CASCADE ON DELETE CASCADE);

create unique index albumid_index on albums (id);
create index albumuserid_index on albums (user_id);
create unique index albumnameid_index on albums (user_id, album_name);

CREATE TABLE IF NOT EXISTS photos (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  album_id INT NOT NULL,
  file_data LONGBLOB NOT NULL,
  preview_data LONGBLOB NOT NULL,

  FOREIGN KEY (album_id) REFERENCES albums(id)
      ON UPDATE CASCADE ON DELETE CASCADE);

create unique index photoid_index on photos (id);
create index photoalbumid_index on photos (album_id);
