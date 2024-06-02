CREATE DATABASE card_app;
CREATE USER card_app_user WITH PASSWORD 'card_app_password';
GRANT ALL PRIVILEGES ON DATABASE card_app TO card_app_user;
\c card_app
GRANT ALL ON SCHEMA public TO card_app_user;
GRANT ALL ON ALL TABLES IN SCHEMA public TO card_app_user;
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO card_app_user;
GRANT ALL ON ALL FUNCTIONS IN SCHEMA public TO card_app_user;