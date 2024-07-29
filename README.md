# Concepteur et résolveur de graphes orienté

Ce projet scolaire est une application desktop réalisé en deuxième année de BUT informatique.

Elle à été réalisé en Java et permet de concevoir des graphes, trouvé le chemin le plus court en deux noeuds du graphe ainsi que de souvegarder et recharger des graphes qui ont été sauvegardé précédemment.

Vous pouvez également personnaliser l'application grâce à différent thème. 

## Compiler et lancer l'application

### Sous Windows

Si voulez compiler puis lancer l'application sous windows il vous suffit de lancer le fichier "compiler_et_lancer.bat"

### Sous Linux

Si vous voulez compiler puis lancer l'application sous linux il vous suffit de lancer le fichier "compiler_et_lancer.sh"

## Lancer l'application

### Sous Windows

Si vous voulez lancer l'application sans la compiler il vous suffit de lancer le fichier "lancer.bat". Si le projet n'est pas compiler il sera automatiquement compiler mais si le projet est déjà compiler il ne sera pas recompiler.

### Sous Linux

Si vous voulez lancer l'application sans la compiler il vous suffit de lancer le fichier "lancer.sh". Si le projet n'est pas compiler il sera automatiquement compiler mais si le projet est déjà compiler il ne sera pas recompiler.

## Fonctionnalité

### Noeuds

Vous pouver créer des noeuds grace au bouton du panel de gauche ou en faisant un clique droit sur un endroit vide du panel de droite.
Vous pouver supprimer des noeuds en faisant un clique droit sur le noeud que vous voulez supprimer. (le cursor change de forme pour vous indiquer que vous pouvez cliquer sur le noeud)
Vous pouver déplacer des noeuds en faisant un clique gauche sur le noeud et que vous vous déplacer en restant appuyer sur le clique gauche. (le cursor change de forme pour vous indiquer que vous pouvez déplacer le noeud)

### Arrêtes

Vous pouver créer des arrêtes grace au bouton et aux deux listes déroulante du panel de gauche. le cout de l'arrête sera demander après appuie sur le bouton, dans une pop up.
Vous pouver supprimer des arrêtes en faisant un clique droit sur un noeud, ensuite une liste apparaitra avec les arrêtes qui partent de ce noeud, choisissez l'arrête que vous voulez supprimer et sera supprimer.
Pour modifier les couts d'une arrête il faut la supprimer puis la recréer.
Le chemin le plus court entre les deux noeuds sélectionner dans les listes déroulantes sera afficher en vert sur le graphe.
Le bouton "Trouver les noeuds obselètes" permet de trouver les noeuds qui n'ont aucune arrête vers d'autre noeuds, autrement ce sont des puits. Ces noeuds seront afficher en rouge sur le graphe.

### Autres

Vous pouver sauvegarder votre graphe en appuyant sur ctrl + s ou en cliquant sur le bouton "sauvegarder" dans le menu "fichier" de la menu barre.
Vous pouver charger un graphe en appuyant sur ctrl + o ou en cliquant sur le bouton "charger" dans le menu "fichier" de la menu barre.

Vous pouvez changer l'apparence de l'application en choisissant un thème dans le menu "préférence" de la menu barre. par défaut le thème est "clair".
Vous pouver afficher ce fichier d'aide en cliquant sur le bouton "Aide" dans le menu "aide" de la menu barre.
