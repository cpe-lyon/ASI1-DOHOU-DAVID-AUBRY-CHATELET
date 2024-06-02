\c card_app
create table app_user_market_sell_proposals
(
    app_user_id              bigint not null
        constraint fk9mei1wbsu4f1gum0dgxxodxkg
            references app_user,
    market_sell_proposals_id bigint not null
        constraint uk_krkpgaum1l3g6606doe8pjps2
            unique
        constraint fkptvsj2acvis6gsulkl7kgk5lu
            references market_sell_proposal_entity
);

alter table app_user_market_sell_proposals
    owner to card_app_user;

