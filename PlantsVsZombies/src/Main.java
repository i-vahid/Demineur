/**
 * Contient la méthode main(), point d'entrée du programme
 */
public class Main {

	public static void main(String[] args) {

		GameWorld world = new GameWorld();

		// reglage de la taille de la fenetre de jeu en pixels
		StdDraw.setCanvasSize(world.getTailleXImageFond(), world.getTailleYImageFond());
		// changement du scale de l'image pour que les images ne soient pas déformées et
		// pour avoir une échelle correspondant au
		// nombre de pixels de l'image
		StdDraw.setXscale(0, world.getTailleXImageFond());
		StdDraw.setYscale(0, world.getTailleYImageFond());

		// permet le double buffering, pour permettre l'animation
		StdDraw.enableDoubleBuffering();

		// la boucle principale du jeu
		while (!GameWorld.gameLost() && !GameWorld.gameWon()) {
			// efface la fenetre graphique
			StdDraw.clear();

			// capture et traite les interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				world.processUserInput(key);
			}
			// capture et traite les clics du joueur
			if (StdDraw.isMousePressed()) {
				world.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}

			// gère la vague de zombies du jeu
			world.creerVagueDeZombies();

			// appelle la méthode qui s'occupe des actions des entites
			world.step();

			// dessine la carte
			world.dessine();

			// gestion des soleils
			world.faireApparaitreSoleils();

			// création de la liste des zombies du jeu
			GameWorld.creerListeDeZombies();

			// Pour faire tirer les TirePois
			world.faireTirerTirePois();

			// Pour faire apparaitre le soleil toutes les 6.5 secondes
			world.faireApparaitreSoleilsCaseAleatoire();

			// supprime les entités nécessaires
			GameWorld.supprimerEntites();
			
			// vérifie si l'utilisateur a perdu
			world.checkDefeat();

			// montre la fenetre graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);

		}

		if (GameWorld.gameWon()) {
			System.out.println("Game won !");
			world.drawVictory();
		}
		if (GameWorld.gameLost()) {
			System.out.println("Game lost...");
			world.drawDefeat();
		}

	}

}
