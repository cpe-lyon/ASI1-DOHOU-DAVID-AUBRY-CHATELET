# Atelier 1
### Loan Aubry - Mathias Chatelet Ferrety - Medhy Dohou - Rémy David

## Respect du pattern MVC
Nos prototypes respectent le pattern MVC car nous avons un controller(RequestCrt) pour la communications des données, des vues(Templates) qui recoivent les données traitées par la partie back-end de notre projet et un model(Card) qui se charge du stockage des données et du traitement des requêtes.

## Avantages/incovénients Web Statique + Web Service

- Avantages :
    - Performance : Les sites web statiques sont plus rapides à charger car les fichiers HTML, CSS et JS viennent directement du serveur sans avoir à générer de contenu dynamique.
    - Sécurité : Ils sont moins vulnérables car ils n'y a pas de base de données ou de code serveur.
    - Évolutivité : Utilisation des réseaux de diffusion de contenu pour distribuer le contenu à plusieurs serveurs.
    - Maintenance : Ils nécessitent moins de maintenance parce qu'ils n'ont pas besoin de mises à jour ou de correctifs de sécurité réguliers.
    - Fiabilité : Ils sont plus fiables car ils ne dépendent pas de la disponibilité d'une base de données ou d'un serveur d'applications.

- Inconvénients :
    - Flexibilité limitée : Les sites web statiques ne peuvent pas être mis à jour dynamiquement, il faut modifier manuellement les fichiers HTML, CSS et JS.
    - Fonctionnalités limitées : Ils ne peuvent supporter certaines fonctionnalités comme la personnalisation du contenu, la recherche sur le site ou la connexion/inscription d'un utilisateur ou bien l'intégration de services tiers.
    - Contenu statique : Ils ne peuvent pas afficher/modifier du contenu et des données en temps réel.

## Avantages/incovénients Web Dynamique

- Avantages :
    - Flexibilité : Les sites web dynamiques peuvent être mis à jour dynamiquement en utilisant des bases de données et des langages (PHP, Java, C#, Python, ...).
    - Fonctionnalités avancées : Ils peuvent supporter des fonctionnalités avancées comme la personnalisation du contenu, la recherche sur le site, la connexion/inscription d'un utilisateur ou bien l'intégration de services tiers.
    - Contenu dynamique : Ils peuvent afficher et modifier du contenu et des données en temps réel.
    - Interactivité : Ils offrent une meilleure expérience utilisateur en proposant un site plus interactif en permettant aux utilisateurs de remplir et d'envoyer des formulaires et d'écrire des commentaires.

- Inconvénients :
    - Performance : Les sites web dynamiques sont plus lents que les sites web statiques à cause du contenu dynamique.
    - Sécurité : Ils sont plus vulnérables aux attaques de sécurité car ils y a une base de données et un serveur.
    - Maintenance : Ils nécessitent plus de maintenance pour s'assurer que tout est à jour.
    - Complexité : Ils sont plus complexes à développer et à maintenir que les sites web statiques à cause de la base de données à gérer et des mise à jour des langages à surveiller.
    - Coût : Ils peuvent être plus coûteux à développer et à maintenir que les sites web statiques car ils sont plus complexe.