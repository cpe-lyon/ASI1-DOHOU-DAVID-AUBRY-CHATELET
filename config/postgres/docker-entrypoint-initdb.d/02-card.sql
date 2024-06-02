\c card_app
create table card
(
    id            bigint default nextval('card_id_seq'::regclass) not null
        primary key,
    affinity      varchar(255),
    attack        real                                            not null,
    defence       real                                            not null,
    description   varchar(255),
    energy        real                                            not null,
    family        varchar(255),
    hp            real                                            not null,
    img_url       varchar(255),
    name          varchar(255),
    price         real                                            not null,
    small_img_url varchar(255)
);

alter table card
    owner to postgres;

grant delete, insert, references, select, trigger, truncate, update on card to card_app_user;

INSERT INTO public.card (id, affinity, attack, defence, description, energy, family, hp, img_url, name, price, small_img_url) VALUES (1, 'Normal', 40, 25, 'Un Pokemon tout mignon', 25, 'pokemon', 30, 'https://www.pokepedia.fr/images/e/e3/M%C3%A9tamorph-RFVF.png', 'Métamorph', 0, 'https://www.pokepedia.fr/images/e/e3/M%C3%A9tamorph-RFVF.png');
INSERT INTO public.card (id, affinity, attack, defence, description, energy, family, hp, img_url, name, price, small_img_url) VALUES (2, 'Fire', 49, 20, 'Un Pokemon tout mignon', 20, 'pokemon', 60, 'https://www.pokepedia.fr/images/thumb/8/89/Salam%C3%A8che-RFVF.png/800px-Salam%C3%A8che-RFVF.png', 'Salameche', 0, 'https://www.pokepedia.fr/images/thumb/8/89/Salam%C3%A8che-RFVF.png/800px-Salam%C3%A8che-RFVF.png');
INSERT INTO public.card (id, affinity, attack, defence, description, energy, family, hp, img_url, name, price, small_img_url) VALUES (3, 'Fire', 34, 10, 'Un Pokemon tout mignon', 15, 'pokemon', 60, 'https://www.pokepedia.fr/images/thumb/8/86/H%C3%A9ricendre-LPA.png/250px-H%C3%A9ricendre-LPA.png', 'Hericendre', 0, 'https://www.pokepedia.fr/images/thumb/8/86/H%C3%A9ricendre-LPA.png/250px-H%C3%A9ricendre-LPA.png');
INSERT INTO public.card (id, affinity, attack, defence, description, energy, family, hp, img_url, name, price, small_img_url) VALUES (4, 'Water', 44, 20, 'Un Pokemon tout mignon', 10, 'pokemon', 55, 'https://www.pokepedia.fr/images/thumb/c/cc/Carapuce-RFVF.png/800px-Carapuce-RFVF.png', 'Carapuce', 0, 'https://www.pokepedia.fr/images/thumb/c/cc/Carapuce-RFVF.png/800px-Carapuce-RFVF.png');
INSERT INTO public.card (id, affinity, attack, defence, description, energy, family, hp, img_url, name, price, small_img_url) VALUES (5, 'Electric', 77, 10, 'Un Pokemon tout mignon', 25, 'pokemon', 49, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0-b7rBnwIrsNmEefoGlyynGv1BjF4RbFz4g&usqp=CAU', 'Pikachu', 0, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ7JS4kuCsRWcTw18CkvdLdm-eiMFjTe8jRw&usqp=CAU');
