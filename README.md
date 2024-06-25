# Projet Moteur de Jeu
> G3
>Nada YASSINE, Ines HAFRAOUI, Rodrigue LEITAO--PEREIRA DIAS, Nathanael CHAUVIN, Julian MAZERAT, Jessy SANFILIPPO

## 1. Récuperer le repositoy
### Clonage 

- Créer un répertoire qui va devenir un répertoire git 
    - git init 
    - git clone *lien ssh*

### Lancement du Jeu

Pour lancer le jeu, 2 manières sont possibles : 
- Executer la méthode main du fichier Game.java dans eclipse. Il se trouve dans game/info3.game. Le choix du jeu se fait dans ce fichier dans la méthode main.
- Se placer dans le répertoire au niveau du dossier info3.game.given.2021 et lancer la commande **make run**

## 2.  Architecture du Projet de Jeu Java
### 2.1 Package Model (Chemin: game/model)

- **game.automaton** : Contient les classes qui implémentent les automates des entités, gérant leur comportement et leurs transitions d'état en fonction des interactions du jeu.
- **game.entity** : Contient les définitions des différentes entités du jeu (comme les joueurs, les ennemis, et les objets interactifs). Ces classes définissent les propriétés et les actions possibles pour chaque type d'entité.
- **game.map** / **game.Biomes** : Implémente la génération des cartes et des biomes du jeu, utilisant divers algorithmes pour créer des environnements de jeu aléatoires et dynamiques.

#### 2.1.1 Fichier de Configuration 
Les fichiers **configjeu1.json** et **configjeu2.json** sont placés directement dans le package model, ils contiennent des champs : 
- les paramètres des entités
    - leur type, 
    - le comportement (le fichier gal et la classe avec la description des actions) et la hitbox
    - le sprite
        -  On y trouve un objet **details** composé d'autre objets. Ils indiquent pour chaque action où trouver les sprites dans la matrice généré avec le fichier .png. L'array details change en fonction de l'entité (exemple entre un joueur et une arrow). Il faut délimiter les sprites par action et par orientation avec soit un entier pour chaque image à selectionner soit un array avec le début et la fin de l'animation.
    - la direction, position, team, et un boolean pickable
- un tableau de fichier gal qui sont les comportements des bots qu'on peut leur attribuer
- cooperative qui indique si les joueurs sont adversaire ou pas
- timer : si il est à -1 la partie n'a pas de limite de temps 
- viscosity : v 
    - v = 0 alors le joueur n'est pas contraint dans son déplacement
    - v > 0 , il est ralentis de v m/s 
    - v < 0 , il accelère de v m/s
- le nombre de bot initiale pour chaque joueur, le nombre de player et le nombre d'item généré aléatoirement sur la map

### 2.2 Package View (Chemin: game/view)
Gère la représentation visuelle du jeu, mettant en œuvre l'affichage des éléments graphiques.

- **info3.game.avatar** : Contient les classes qui gèrent les avatars des entités dans le jeu, transformant les états des modèles en représentations visuelles que les joueurs peuvent voir et avec lesquelles ils peuvent interagir.
- **info3.game.view** : Contient les classes qui gèrent la vue du jeu dont une vue principale contenant des viewports et autres éléments de gameplay qui seront affiché sur la fenêtre.

### 2.3 Package Controller 
- **CanvasListener** : Implémenté dans ce package, ce contrôleur écoute et répond aux interactions de l'utilisateur, comme les clics de souris ou les entrées clavier, en manipulant le modèle et la vue en conséquence.

### 2.4 Package Game
- **info3.game** : Contient le point d'entrée principal (main) du jeu, initialisant et lançant le moteur de jeu, en liant toutes les composantes du modèle, de la vue, et du contrôleur.

## 3. Lien de la vidéo
https://youtu.be/QWrw4YiCPCg

## 4. Pourcentage de participation
- Nada YASSINE : 16,66%
- Ines HAFRAOUI : 16,66%
- Rodrigue LEITAO--PEREIRA DIAS : 16,66%
- Nathanael CHAUVIN : 16,66%
- Julian MAZERAT : 16,66%
- Jessy SANFILIPPO : 16,66%
