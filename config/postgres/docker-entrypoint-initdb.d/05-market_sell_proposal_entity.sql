\c card_app
create table market_sell_proposal_entity
(
    id        bigint           not null
        primary key,
    card_id   bigint,
    price     double precision not null,
    seller_id bigint
        constraint fk6g56xntf7wx3roi5r0pubxxe7
            references app_user
);

alter table market_sell_proposal_entity
    owner to card_app_user;

INSERT INTO public.market_sell_proposal_entity (id, card_id, price, seller_id) VALUES (1, 1, 20, 1);
