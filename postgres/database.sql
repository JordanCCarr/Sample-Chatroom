create table users (
    user_id serial primary key,
    username varchar(50) unique not null
);

create table chatrooms (
    room_id serial primary key,
    room_name varchar(255) unique not null
);

create table messages (
    message_id serial primary key,
    message_timestamp timestamp,
    room_name varchar(255),
    username varchar(50),
    message_content varchar(1000)
);

alter table messages
    add constraint rn_constraint
    foreign key (room_name)
    references chatrooms (room_name);

alter table messages
    add constraint un_constraint
    foreign key (username)
    references users (username);
