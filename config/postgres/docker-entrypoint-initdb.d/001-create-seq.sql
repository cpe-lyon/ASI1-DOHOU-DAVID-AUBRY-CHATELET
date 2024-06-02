\c card_app

create sequence card_id_seq;

alter sequence card_id_seq owner to postgres;

grant select, update, usage on sequence card_id_seq to card_app_user;

create sequence app_user_seq
    increment by 50;

alter sequence app_user_seq owner to card_app_user;

create sequence history_seq
    increment by 50;

alter sequence history_seq owner to card_app_user;

create sequence market_sell_proposal_entity_seq
    increment by 50;

alter sequence market_sell_proposal_entity_seq owner to card_app_user;

