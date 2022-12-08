drop table student cascade;
drop table teacher cascade ;
drop table lesson cascade ;
drop table favourite_lesson;
drop table subscriber;
drop table file_info;

create table file_info
(
    id  bigserial primary key,
    original_file_name varchar(100),
    storage_file_name varchar(100) not null,
    size bigint not null,
    type varchar(100)
);

create table student
(
    id          bigserial primary key,
    first_name  varchar(30),
    last_name   varchar(30),
    email       varchar(30) unique,
    password    varchar(100),
    photo_id bigint references file_info(id),
    status      varchar(8),
    tg_username      varchar(30)
);

create table teacher
(
    id          bigserial primary key,
    first_name  varchar(30),
    last_name   varchar(30),
    email       varchar(30) unique,
    password    varchar(100),
    status      varchar(8),
    photo_id bigint references file_info(id),
    description varchar(300),
    work_experience integer,
    tg_username varchar(30)
);



create table lesson
(
    id  bigserial primary key,
    title varchar(30),
    description varchar(300),
    file_id bigint references file_info(id),
    teacher_id bigint references teacher(id),
    video_link varchar(255)
);

create table subscriber
(
    id  bigserial primary key,
    teacher_id bigint references teacher(id),
    student_id bigint references student(id)
);

create table favourite_lesson
(
    id  bigserial primary key,
    lesson_id bigint references lesson(id),
    student_id bigint references student(id)
);