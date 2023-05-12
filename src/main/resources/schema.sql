create table if not exists users
(
    id          int          not null generated by default as identity primary key,
    user_id     bigint       not null unique,
    user_name   varchar(64)  not null,
    user_avatar varchar(256) not null
);

create table if not exists guilds
(
    id         int          not null generated by default as identity primary key,
    guild_id   bigint       not null unique,
    guild_name varchar(64)  not null,
    guild_icon varchar(256) not null
);