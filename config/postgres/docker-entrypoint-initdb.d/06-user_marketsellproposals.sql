\c card_app
create table user_marketsellproposals
(
    marketsellproposal_id bigint not null
        constraint fk97t7cy7svi2rrrpi0nlupgrjy
            references card,
    app_user_id           bigint not null
        constraint fkt7yoywraenr5ijax41sfvsc19
            references app_user
);

alter table user_marketsellproposals
    owner to card_app_user;

