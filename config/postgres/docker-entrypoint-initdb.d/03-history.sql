\c card_app
create table history
(
    id      bigint           not null
        primary key,
    card_id bigint,
    price   double precision not null,
    type    varchar(255),
    user_id bigint
);

alter table history
    owner to card_app_user;

