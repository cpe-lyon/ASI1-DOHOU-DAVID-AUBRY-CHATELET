-- Adminer 4.8.1 PostgreSQL 16.3 dump

\connect "card_app";

CREATE SEQUENCE card_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."card" (
                                 "id" bigint DEFAULT nextval('card_id_seq') NOT NULL,
                                 "affinity" character varying(255),
                                 "attack" real NOT NULL,
                                 "defence" real NOT NULL,
                                 "description" character varying(255),
                                 "energy" real NOT NULL,
                                 "family" character varying(255),
                                 "hp" real NOT NULL,
                                 "img_url" character varying(255),
                                 "name" character varying(255),
                                 "price" real NOT NULL,
                                 "small_img_url" character varying(255),
                                 CONSTRAINT "card_pkey" PRIMARY KEY ("id")
) WITH (oids = false);
GRANT ALL ON TABLE card TO card_app_user;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO card_app_user;

INSERT INTO "card" ("id", "affinity", "attack", "defence", "description", "energy", "family", "hp", "img_url", "name", "price", "small_img_url") VALUES
                                                                                                                                                     (1,	'Normal',	40,	25,	'Un Pokemon tout mignon',	25,	'pokemon',	30,	'https://www.pokepedia.fr/images/e/e3/M%C3%A9tamorph-RFVF.png',	'Métamorph',	0,	'https://www.pokepedia.fr/images/e/e3/M%C3%A9tamorph-RFVF.png'),
                                                                                                                                                     (2,	'Fire',	49,	20,	'Un Pokemon tout mignon',	20,	'pokemon',	60,	'https://www.pokepedia.fr/images/thumb/8/89/Salam%C3%A8che-RFVF.png/800px-Salam%C3%A8che-RFVF.png',	'Salameche',	0,	'https://www.pokepedia.fr/images/thumb/8/89/Salam%C3%A8che-RFVF.png/800px-Salam%C3%A8che-RFVF.png'),
                                                                                                                                                     (3,	'Fire',	34,	10,	'Un Pokemon tout mignon',	15,	'pokemon',	60,	'https://www.pokepedia.fr/images/thumb/8/86/H%C3%A9ricendre-LPA.png/250px-H%C3%A9ricendre-LPA.png',	'Hericendre',	0,	'https://www.pokepedia.fr/images/thumb/8/86/H%C3%A9ricendre-LPA.png/250px-H%C3%A9ricendre-LPA.png'),
                                                                                                                                                     (4,	'Water',	44,	20,	'Un Pokemon tout mignon',	10,	'pokemon',	55,	'https://www.pokepedia.fr/images/thumb/c/cc/Carapuce-RFVF.png/800px-Carapuce-RFVF.png',	'Carapuce',	0,	'https://www.pokepedia.fr/images/thumb/c/cc/Carapuce-RFVF.png/800px-Carapuce-RFVF.png'),
                                                                                                                                                     (5,	'Electric',	77,	10,	'Un Pokemon tout mignon',	25,	'pokemon',	49,	'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0-b7rBnwIrsNmEefoGlyynGv1BjF4RbFz4g&usqp=CAU',	'Pikachu',	0,	'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRZ7JS4kuCsRWcTw18CkvdLdm-eiMFjTe8jRw&usqp=CAU');

-- 2024-05-27 07:07:03.994705+00

