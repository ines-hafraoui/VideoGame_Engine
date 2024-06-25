# Projet Moteur de Jeu
> G3
>Nada YASSINE, Ines HAFRAOUI, Rodrigue LEITAO--PEREIRA DIAS, Nathanael CHAUVIN, Julian MAZERAT, Jessy SANFILIPPO

## 1. Récuperer le repositoy
### Clonage 

- Créer un répertoire qui va devenir un répertoire git 
    - git init 
    - git clone <lien ssh>
    - git add --all
    - git commit -m "Initial commit"
    - git push --all

### Lancement du Jeu

- Commencer par compiler le fichier parser.jj pour parser les fichiers .gal. 
Il se trouve dans gal/gal.parser. Sur eclipse un bouton de compilation vous est proposé. 

- Pour lancer le jeu, il faut éxecuter la méthode main du fichier Game.java. 
Il se trouve dans game/info3.game

## 2.  Architecture du Projet de Jeu Java
### 2.1 Package Model (Chemin: game/model)

- **game.automaton** : Contient les classes qui implémentent les automates des entités, gérant leur comportement et leurs transitions d'état en fonction des interactions du jeu.
- **game.entity** : Contient les définitions des différentes entités du jeu (comme les joueurs, les ennemis, et les objets interactifs). Ces classes définissent les propriétés et les actions possibles pour chaque type d'entité.
- **game.map** / **game.Biomes** : Implémente la génération des cartes et des biomes du jeu, utilisant divers algorithmes pour créer des environnements de jeu aléatoires et dynamiques.

#### Fichier de Configuration 
Les fichiers **.json** sont placés directement dans le package model, ces fichiers contiennent la configuration du jeu, y compris les paramètres des entités, les règles des automates, et les spécifications des cartes.

### 2.2 Package View (Chemin: game/view)
Gère la représentation visuelle du jeu, mettant en œuvre l'affichage des éléments graphiques.

- **game.avatar** : Contient les classes qui gèrent les avatars des entités dans le jeu, transformant les états des modèles en représentations visuelles que les joueurs peuvent voir et avec lesquelles ils peuvent interagir.

### 2.3 Package Controller 
- **CanvasListener** : Implémenté dans ce package, ce contrôleur écoute et répond aux interactions de l'utilisateur, comme les clics de souris ou les entrées clavier, en manipulant le modèle et la vue en conséquence.

### 2.4 Package Game
- **info3.game** : Contient le point d'entrée principal (main) du jeu, initialisant et lançant le moteur de jeu, en liant toutes les composantes du modèle, de la vue, et du contrôleur.

## 3. Lien de la vidéo 
## 4. Pourcentage de participation
- Nada YASSINE : 16,66%
- Ines HAFRAOUI : 16,66%
- Rodrigue LEITAO--PEREIRA DIAS : 16,66%
- Nathanael CHAUVIN : 16,66%
- Julian MAZERAT : 16,66%
- Jessy SANFILIPPO : 16,66%
