\c card_app
create table user_card
(
    app_user_id bigint not null
        constraint fkjgllsjgwdl5k4u0qsh2ui1gx4
            references app_user,
    card_id     bigint not null
        constraint fk441bl0wnj3hxcj8c18lyxw9e1
            references card
);

alter table user_card
    owner to card_app_user;

INSERT INTO public.user_card (app_user_id, card_id) VALUES (1, 1);
INSERT INTO public.user_card (app_user_id, card_id) VALUES (1, 2);
INSERT INTO public.user_card (app_user_id, card_id) VALUES (1, 3);
INSERT INTO public.user_card (app_user_id, card_id) VALUES (1, 4);
INSERT INTO public.user_card (app_user_id, card_id) VALUES (1, 5);
