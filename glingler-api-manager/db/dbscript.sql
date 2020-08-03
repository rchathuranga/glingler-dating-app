-- we don't know how to generate root <with-no-name> (class Root) :(
create table status_category
(
    status_category_code varchar(5)  not null
        primary key,
    description          varchar(50) null
);

create table status
(
    status_code          varchar(5)  not null,
    description          varchar(50) null,
    status_category_code varchar(5)  null,
    constraint status_status_code_uindex
        unique (status_code),
    constraint status_status_category_status_category_code_fk
        foreign key (status_category_code) references status_category (status_category_code)
);

alter table status
    add primary key (status_code);

create table system_user_type
(
    system_user_type_id   int auto_increment
        primary key,
    system_user_type_code varchar(10) null,
    status                varchar(10) null
);

create table user_types
(
    user_type_id   int auto_increment
        primary key,
    user_type_code varchar(10) null,
    description    varchar(50) null,
    status         varchar(10) null
);

create table user
(
    user_id             int auto_increment
        primary key,
    username            varchar(50) null,
    password            varchar(50) null,
    password_status     varchar(10) null,
    login_attempts      int         null,
    status              varchar(50) null,
    email               varchar(50) null,
    mobile_no           varchar(15) null,
    user_type_id        int         null,
    system_user_type_id int         null,
    is_verified         tinyint     null,
    constraint user_username_uindex
        unique (username),
    constraint user_system_user_type_system_user_type_id_fk
        foreign key (system_user_type_id) references system_user_type (system_user_type_id),
    constraint user_user_types_user_type_id_fk
        foreign key (user_type_id) references user_types (user_type_id)
);

create table profile
(
    profile_id   int auto_increment
        primary key,
    first_name   varchar(50)  null,
    last_name    varchar(50)  null,
    bio          varchar(150) null,
    sex          varchar(10)  null,
    age          int          null,
    birthday     timestamp    null,
    image_url    text         null,
    user_id      int          null,
    created_time timestamp    null,
    status       varchar(10)  null,
    constraint profile_user_user_id_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
);

create table activity
(
    activity_id  int auto_increment
        primary key,
    content      text        null,
    img_url      text        null,
    profile_id   int         null,
    react_count  int         null,
    created_time timestamp   null,
    status       varchar(10) null,
    constraint activity_profile_profile_id_fk
        foreign key (profile_id) references profile (profile_id)
);

create index activity_status_status_code_fk
    on activity (status);

create table comment
(
    comment_id           int auto_increment
        primary key,
    comment              text        null,
    commentor_profile_id int         null,
    activity_id          int         null,
    created_time         timestamp   null,
    status               varchar(10) null,
    constraint comment_activity_activity_id_fk
        foreign key (activity_id) references activity (activity_id),
    constraint comment_profile_profile_id_fk
        foreign key (commentor_profile_id) references profile (profile_id)
);

create index comment_status_status_code_fk
    on comment (status);

create table filter
(
    filter_id          int auto_increment
        primary key,
    age                int            null,
    interested_on      varchar(10)    null,
    age_ranga_start    int            null,
    age_range_end      int            null,
    distance           decimal(20, 5) null,
    location_longitude varchar(20)    null,
    location_latitude  varchar(20)    null,
    location_desc      varchar(50)    null,
    profile_id         int            null,
    constraint filter_profile_profile_id_fk
        foreign key (profile_id) references profile (profile_id)
);

create table `match`
(
    match_id         int auto_increment
        primary key,
    profile_id       int         null,
    match_profile_id int         null,
    status           varchar(10) null,
    create_time      timestamp   null,
    constraint match_profile_profile_id_fk
        foreign key (profile_id) references profile (profile_id),
    constraint match_profile_profile_id_fk_2
        foreign key (match_profile_id) references profile (profile_id)
);

create table chat
(
    chat_id              int auto_increment
        primary key,
    match_id             int       null,
    chat_send_profile_id int       null,
    message              text      null,
    created_time         timestamp null,
    constraint chat_match_match_id_fk
        foreign key (match_id) references `match` (match_id),
    constraint chat_profile_profile_id_fk
        foreign key (chat_send_profile_id) references profile (profile_id)
);

create index match_status_status_code_fk
    on `match` (status);

create index profile_status_status_code_fk
    on profile (status);

create table story
(
    story_id    int auto_increment
        primary key,
    img_url     text        null,
    status      varchar(10) null,
    create_time timestamp   null,
    profile_id  int         null,
    constraint story_profile_profile_id_fk
        foreign key (profile_id) references profile (profile_id)
);

create index story_status_status_code_fk
    on story (status);

create index user_status_status_code_fk
    on user (status);

