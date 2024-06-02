\c card_app
create table app_user
(
    id       bigint           not null
        primary key,
    email    varchar(255)     not null
        constraint uk_1j9d9a06i600gd43uu3km82jw
            unique,
    money    double precision not null,
    password varchar(255)     not null,
    username varchar(255)     not null
);

alter table app_user
    owner to card_app_user;

INSERT INTO public.app_user (id, email, money, password, username) VALUES (1, 'medhy.doihou@gmail.com', 0, 'medhy', 'test');
INSERT INTO public.app_user (id, email, money, password, username) VALUES (2, 'test', 999, 'test', 'test2');
