-- Adminer 4.8.1 PostgreSQL 16.3 dump

\connect "card_app";

CREATE SEQUENCE user_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1;

CREATE TABLE "public"."app_user" (
                                     "id" bigint NOT NULL,
                                     "email" character varying(255) NOT NULL,
                                     "money" double precision NOT NULL,
                                     "password" character varying(255) NOT NULL,
                                     "username" character varying(255) NOT NULL,
                                     CONSTRAINT "app_user_pkey" PRIMARY KEY ("id"),
                                     CONSTRAINT "uk_1j9d9a06i600gd43uu3km82jw" UNIQUE ("email")
) WITH (oids = false);
GRANT ALL ON TABLE app_user TO card_app_user;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO card_app_user;

INSERT INTO "app_user" ("id", "email", "money", "password", "username") VALUES (-1, 'mail', 'o', 'oi', 21);


-- 2024-05-28 12:20:16.40547+00