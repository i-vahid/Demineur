import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Représente l'univers du jeu
 */
public class GameWorld {

	// taille de l'image de fond = 920*590
	// hauteur et largeur d'une case 100
	/** Taille de l'image de fond */
	private static int tailleXImageFond = 920;
	private static int tailleYImageFond = 590;
	/** Marges entre les bords de l'image et la grille de l'image */
	private static int margeGauche = 170;
	private static int margeBas = 23;
	private static int margeHaut = 90;
	private static int margeDroite = 15;
	/** Taille d'une case */
	private static double tailleYCase = 96;
	private static double tailleXCase = 81.7;
	// Résoud petit décalage
	private static int gap = 3;
	/** Liste des {@link Entite} actuellement présentes dans le jeu */
	public static List<Entite> entites;
	/** Liste des lignes comprenant actuellement un {@link Zombie} dans le jeu */
	public static Set<Integer> lignesComprenantUnZombie = new TreeSet<Integer>();
	/** Liste des {@link Zombie} actuellement présents dans le jeu */
	public static List<Zombie> listeDeZombies = new LinkedList<Zombie>();
	/**
	 * Liste des {@link Entite} actuellement présentes dans le jeu et qu'il faut
	 * détruire
	 */
	public static List<Entite> entitesToRemove = new LinkedList<Entite>();
	/** Pour savoir si la partie est gagnee ou pas */
	private static boolean gameWon;
	private static boolean gameLost;
	/**
	 * Création d'un {@link Timer} pour ne pas capturer plusieurs clics au même
	 * instant
	 */
	private Timer delaiClic;
	/** Temps entre deux vagues de <b>Zombie</b> */
	private Timer dureeVagueZombie;
	/**
	 * Le type de plante à planter. Peut être changé en cliquant sur une
	 * {@link Afficheur.Carte} ou avec le clavier
	 */
	private char planteAPlanter;
	public static CompteurSoleils compteurSoleil;
	/** Pour la méthode @see faireApparaitreSoleilsCaseAleatoire() */
	private Timer tmpsSoleils;
	/** Pour la méthode @see faireApparaitreSoleilsCaseAleatoire() */
	private Timer tmpsClic;
	/** Pour numéroter la vague de zombies actuelle @see creerVagueDeZombies() */
	private int vagueZombie;
	/**
	 * Pour compter le nombre de fois que la 4ème vague de zombies apparait
	 * 
	 * @see creerVagueDeZombies()
	 */
	private int nbVaguesZombies4;

	public GameWorld() {
		gameWon = false;
		gameLost = false;
		// on cree les collections
		entites = new LinkedList<Entite>();
		// création de l'afficheur
		Afficheur afficheur = new Afficheur(230, this.getTailleYImageFond() - 40);
		GameWorld.entites.add(afficheur);
		// création du compteur
		GameWorld.compteurSoleil = new CompteurSoleils(-100, -100);
		GameWorld.entites.add(GameWorld.compteurSoleil);
		GameWorld.entites.add(afficheur.carteTirePois);
		GameWorld.entites.add(afficheur.carteTournesol);
		GameWorld.entites.add(afficheur.carteNoix);
		this.tmpsSoleils = new Timer(6500);
		this.vagueZombie = 1;
		this.nbVaguesZombies4 = 0;
	}

	/**
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		switch (key) {
		case 't':
			System.out.println("Le joueur veut planter un Tournesol...");
			this.planteAPlanter = 't';
			break;
		case 'p':
			System.out.println("Le joueur veut planter un Tire-Pois...");
			this.planteAPlanter = 'p';
			break;
		case 'n':
			System.out.println("Le joueur veut planter une Noix...");
			this.planteAPlanter = 'n';
			break;
		default:
			System.out.println("Touche non prise en charge");
			break;
		}
	}

	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a été cliquée)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y) {
		if ((this.delaiClic == null || this.delaiClic.hasFinished())) {
			if (!this.clicSurUnSoleil(x, y) && !this.clicSurUneCarte(x, y)
					&& (this.planteAPlanter == 'p' || this.planteAPlanter == 't' || this.planteAPlanter == 'n')) {
				Integer[] coord = this.getCoordXYClicPourImage(x, y);
				// vérification que la coordonnée du clic est une case
				if (coord[0] > GameWorld.margeGauche && coord[0] < GameWorld.tailleXImageFond - GameWorld.margeDroite
						&& coord[1] > GameWorld.margeBas
						&& coord[1] < GameWorld.tailleYImageFond - GameWorld.margeHaut) {
					// vérification qu'aucune Entite n'est déjà présente sur la case (on ne peut pas
					// planter une plante sur une plante,
					// et on ne peut pas planter une plante sur un zombie)
					int ligne = this.getNumeroLigne(y);
					int colonne = this.getNumeroColonne(x);
					for (Entite e : GameWorld.entites) {
						if (!(e instanceof Pois)) {
							int ligneEntite = this.getNumeroLigne(e.getY());
							int colonneEntite = this.getNumeroColonne(e.getX());
							if (ligne == ligneEntite && colonne == colonneEntite) {
								System.out.println("Case occupée par une Entité");
								return;
							}
						}
					}
					Plant next = null;
					int coutPlante = 0;
					switch (this.planteAPlanter) {
					case 'p':
						next = new TirePois(coord[0], coord[1]);
						coutPlante = TirePois.coutSoleil;
						break;
					case 't':
						next = new Tournesol(coord[0], coord[1]);
						coutPlante = Tournesol.coutSoleil;
						break;
					case 'n':
						next = new Noix(coord[0], coord[1]);
						coutPlante = Noix.coutSoleil;
						break;
					}
					if (!GameWorld.entites.contains(next) && GameWorld.compteurSoleil.getValeur() >= coutPlante) {
						GameWorld.entites.add(next);
						GameWorld.compteurSoleil.setValeur(GameWorld.compteurSoleil.getValeur() - coutPlante);
						// redemarre timer charge
						next.restartTimerRecharge();
					}
				}
			}
			this.delaiClic = new Timer(500);
			System.out.println("La souris a été cliquée en : " + x + " - " + y);
		}
	}

	/**
	 * Est utile pour centrer les coordonnées lors d'un clic sur une case <br>
	 * <i>ex: l'utilisateur clique en haut à droite d'une case, la méthode va
	 * renvoyer les bonnes coordonnées pour que l'image soit centrée au milieu de la
	 * case</i>
	 * 
	 * @param x la coordonnée x cliquée
	 * @param y la coordonnée y cliquée
	 * @return les coordonees x et y de l'image à ajouter pour que celle-ci soit
	 *         centrée
	 */
	private Integer[] getCoordXYClicPourImage(double x, double y) {
		int indiceCaseX = (int) Math.floor(((x - GameWorld.margeGauche) / GameWorld.tailleXCase));
		int indiceCaseY = (int) Math.floor((y - GameWorld.margeBas) / GameWorld.tailleYCase);

		double coordX = indiceCaseX * GameWorld.tailleXCase + GameWorld.margeGauche + 45;
		double coordY = indiceCaseY * GameWorld.tailleYCase + GameWorld.margeBas + 45 + GameWorld.gap;

		Integer[] res = new Integer[2];
		res[0] = (int) (coordX);
		res[1] = (int) (coordY);
		return res;
	}

	/**
	 * @param y la coordonnee y de l'image
	 * @return le numéro de la ligne dans laquelle est l'image
	 */
	public static int getNumeroLigne(double y) {
		return ((int) Math.floor((y - margeBas) / GameWorld.tailleYCase));
	}

	/**
	 * @param x la coordonnee x de l'image
	 * @return le numéro de la colonne dans laquelle est l'image
	 */
	public static int getNumeroColonne(double x) {
		return (int) Math.floor(((x - GameWorld.margeGauche) / GameWorld.tailleXCase));
	}

	/**
	 * À appeler dans la boucle du jeu dans la classe Main, méthode main(), pour
	 * faire tirer les {@link TirePois}
	 */
	public void faireTirerTirePois() {
		List<Pois> poisARajouter = new LinkedList<Pois>();
		for (Entite e : GameWorld.entites) {
			if (e instanceof Zombie && (e.getX() <= this.getTailleXImageFond() + 70)) {
				GameWorld.lignesComprenantUnZombie.add(GameWorld.getNumeroLigne(e.getY()));
			}
		}
		for (Entite z : GameWorld.entites) {
			if (z instanceof TirePois) {
				TirePois tp = (TirePois) z;
				if (tp.peutTirer()) {
					poisARajouter.add(tp.tire());
				}
			}
		}
		for (Pois p : poisARajouter) {
			GameWorld.entites.add(p);
		}
		GameWorld.lignesComprenantUnZombie = new TreeSet<Integer>();
	}

	/**
	 * Créé la liste des {@link Zombie} actuellement en jeu
	 */
	public static void creerListeDeZombies() {
		GameWorld.listeDeZombies = new LinkedList<Zombie>();
		for (Entite e : GameWorld.entites) {
			if (e instanceof Zombie) {
				Zombie zBis = (Zombie) e;
				GameWorld.listeDeZombies.add(zBis);
			}
		}
	}

	/**
	 * Créé le scénario d'apparition de zombies du jeu <br>
	 * Les zombies arrivent de plus en plus nombreux au fur et à mesure du temps.
	 */
	public void creerVagueDeZombies() {
		int caseApparitionZombie = 0;
		if (Zombie.delaiDebutPartie.hasFinished() && this.vagueZombie == 1) {
			// 1ère vague : 1 zombie
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			caseApparitionZombie = 2;
			this.dureeVagueZombie = new Timer(25_000);
			this.vagueZombie = 2;
		}
		// 2ème vague : 3 zombies
		if (this.dureeVagueZombie != null && this.dureeVagueZombie.hasFinished() && this.vagueZombie == 2) {
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			caseApparitionZombie = 2;
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			this.dureeVagueZombie.restart();
			this.vagueZombie = 3;
		}
		// 3ème vague : 1 zombie
		if (this.dureeVagueZombie != null && this.dureeVagueZombie.hasFinished() && this.vagueZombie == 3) {
			caseApparitionZombie = 4;
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			this.dureeVagueZombie.restart();
			this.vagueZombie = 4;
		}
		// dernière vague : 3 zombies
		// la dernière vague se répète 4 fois
		if (this.dureeVagueZombie != null && this.dureeVagueZombie.hasFinished() && this.vagueZombie == 4
				&& this.nbVaguesZombies4 < 4) {
			caseApparitionZombie = 1;
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			caseApparitionZombie = 2;
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			caseApparitionZombie = 3;
			GameWorld.entites.add(new ZombieDeBase(GameWorld.tailleXImageFond + 50,
					caseApparitionZombie * GameWorld.tailleYCase + GameWorld.margeBas + 50 + GameWorld.gap));
			this.dureeVagueZombie.restart();
			this.nbVaguesZombies4++;
		}

		// s'il ne reste plus aucun zombie en jeu et que nbVaguesZombies == 3, le jeu
		// est gagné
		if (nbVaguesZombies4 == 4) {
			if (GameWorld.listeDeZombies.isEmpty() && GameWorld.gameWon == false) {
				GameWorld.gameWon = true;
			}
		}

	}

	/**
	 * Est utile pour supprimer les entites qui doivent l'être <br>
	 * A appeler à chaque boucle du jeu
	 */
	public static void supprimerEntites() {
		for (Entite e : GameWorld.entitesToRemove) {
			GameWorld.entites.remove(e);
		}
		GameWorld.entitesToRemove = new LinkedList<Entite>();
	}

	/**
	 * Fait apparaitre les {@link Soleil} des {@link Tournesol} qui ont fini d'en
	 * produire un
	 */
	public void faireApparaitreSoleils() {
		List<Soleil> soleilsToAdd = new LinkedList<Soleil>();
		for (Entite e : GameWorld.entites) {
			if (e instanceof Tournesol) {
				if (((Tournesol) e).getTimerProductionSoleil().hasFinished()) {
					((Tournesol) e).getTimerProductionSoleil().restart();
					soleilsToAdd.add(new Soleil(e.getX(), e.getY()));
				}
			}
		}
		for (Soleil s : soleilsToAdd) {
			GameWorld.entites.add(s);
		}
	}

	/**
	 * Fait apparaitre un {@link Soleil} toutes les 6.5 secondes sur une case
	 * aléatoire du jeu <br> Si au bout de 20 secondes le joueur n'a pas cliqué sur le
	 * soleil, celui-ci disparait et le {@link CompteurSoleils} est incrémenté de 25
	 */
	public void faireApparaitreSoleilsCaseAleatoire() {
		if (this.tmpsSoleils.hasFinished()) {
			double x = ThreadLocalRandom.current().nextDouble(GameWorld.margeGauche,
					GameWorld.tailleXImageFond - GameWorld.margeDroite);
			double y = ThreadLocalRandom.current().nextDouble(GameWorld.margeBas,
					GameWorld.tailleYImageFond - GameWorld.margeHaut);
			Integer[] coord = this.getCoordXYClicPourImage(x, y);
			GameWorld.entites.add(new Soleil(coord[0], coord[1]));
			this.tmpsSoleils.restart();
		}
	}

	/**
	 * @param x coordonnée x du clic
	 * @param y coordonnée y du clic
	 * @return true ssi le clic a été effectué sur un {@link Soleil}
	 */
	public boolean clicSurUnSoleil(double x, double y) {
		for (Entite e : GameWorld.entites) {
			if (e instanceof Soleil) {
				if (x >= (e.getX() - (e.getTailleX() / 2)) && x <= (e.getX() + (e.getTailleX() / 2))
						&& GameWorld.getNumeroLigne(e.getY()) == GameWorld.getNumeroLigne(y)) {
					// clic sur un soleil
					// incrémente le compteur de points soleil
					GameWorld.compteurSoleil.setValeur(GameWorld.compteurSoleil.getValeur() + 25);
					// supprime le soleil
					GameWorld.entitesToRemove.add(e);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param x coordonnée x du clic
	 * @param y coordonnée y du clic
	 * @return true ssi le clic a été effectué sur une {@link Afficheur.Carte }
	 */
	public boolean clicSurUneCarte(double x, double y) {
		for (Entite e : GameWorld.entites) {
			if (e instanceof Afficheur.Carte) {
				if (x >= (e.getX() - (e.getTailleX() / 2)) && x <= (e.getX() + (e.getTailleX() / 2))
						&& GameWorld.getNumeroLigne(e.getY()) == GameWorld.getNumeroLigne(y)) {
					// clic sur une carte
					Afficheur.Carte c = (Afficheur.Carte) e;
					if (GameWorld.compteurSoleil.getValeur() >= c.getCoutSoleil() && c.timerRechargeEntiteRepresentee(c.getEntiteRepresentee())) {
						this.planteAPlanter = c.getEntiteRepresentee();
					}
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Dessine un texte à l'écran en cas de victoire
	 */
	public void drawVictory() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(GameWorld.tailleXImageFond / 2, GameWorld.tailleYImageFond / 2, 200, 200);
		StdDraw.setPenColor(StdDraw.WHITE);
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);
		StdDraw.text(GameWorld.tailleXImageFond / 2, GameWorld.tailleYImageFond / 2, "PARTIE GAGNEE");
		StdDraw.show();
	}
	
	/**
	 * Dessine un texte à l'écran en cas de défaite
	 */
	public void drawDefeat() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(GameWorld.tailleXImageFond / 2, GameWorld.tailleYImageFond / 2, 200, 200);
		StdDraw.setPenColor(StdDraw.WHITE);
		Font font = new Font("Arial", Font.BOLD, 20);
		StdDraw.setFont(font);
		StdDraw.text(GameWorld.tailleXImageFond / 2, GameWorld.tailleYImageFond / 2, "GAME OVER");
		StdDraw.show();
	}
	
	/**
	 * Vérifie à chaque tour de boucle si un Zombie a réussit à franchir la grille
	 * Si c'est le cas le joueur a perdu
	 */
	public void checkDefeat() {
		for(Zombie z : GameWorld.listeDeZombies) {
			if(z.getX() < this.margeGauche) {
				this.gameLost = true;
				return;
			}
		}
	}

	/**
	 * Fait bouger / agir toutes les entites
	 */
	public void step() {
		for (Entite entite : GameWorld.entites)
			entite.step();
	}

	/**
	 * Dessine les entites présentes en jeu
	 */
	public void dessine() {

		// image de fond
		StdDraw.picture(this.getTailleXImageFond() / 2, this.getTailleYImageFond() / 2, "images/ajoutees/map.png");

		// affiche les entites
		for (Entite entite : GameWorld.entites)
			entite.dessine();
	}

	/**
	 * @return true ssi le jeu est gagné
	 */
	public static boolean gameWon() {
		return gameWon;
	}

	/**
	 * @return true ssi le jeu est perdu
	 */
	public static boolean gameLost() {
		return gameLost;
	}

	public int getTailleXImageFond() {
		return tailleXImageFond;
	}

	public int getTailleYImageFond() {
		return tailleYImageFond;
	}

}
