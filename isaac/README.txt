Projet PO – THE  BINDING OF ISAAC
Nom , prénom : EL MELLALI , Yasser   /  TOURANG , Vahid

L’objectif de ce projet c’est une version simple de jeu THE BINDING OF ISAAC en Java en utilisant de library stdDraw.

Le nombre de tile utilisé pour ce jeu est 9x9 qui est défini dans la class roomInfos dans ressources. 
Le caractère principale est Isaac que est défini dans la class Hero qui possède plusieurs attributs comme une liste de larmes pour tirer. Aussi les méthodes move dans chaque direction (UP DOWN LEFT RIGHT ) et tirer vers 4 direction sont définis dans cette class.

Pour les monstre de jeu , on a défini une class abstract de monstre. La méthode move pour chaque monstre c’est diffèrent donc on l’a défini en abstract .
Les attributes comme la vitesse , damage , health et etc sont defini dans resources  par exemple tearsInfos , monstreInfos et … .

1) pour la methode MOVE , on utilise W,A,S,D pour aller vers haut,gauche,bas et droit.
On a commencé à developper notre jeu en faisant les differents rooms et ajouter les portes et decrir le chemin d’access entre chaque room.
On a defini une class mere  ‘ room ‘ dans laquelle on a defini les collision et toutes les changement se passe dans le updateRoom(). Par exemple monstreContact ou bien objectContact que on a fait en plus . 

On a class abstract de obstacles et projectile et class mère obj pour la distinction entre la class  passive et consommable.

Par exemple on a defini ‘KEY’ pour access au room_Boss et c’est un obj consommable. Et dans la class key , on a ajoute le room , pour savoir quel port va être ouvert avec cette cle.

On a evitè d'utiliser l'access des rooms aleatoirement mais par contre les spiders et les snakes qui nous avons ajouté en plus il bouge dans le direction aleatoire.


