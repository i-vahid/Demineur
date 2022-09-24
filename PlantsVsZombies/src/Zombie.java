/**
 * Modèle pour les zombies du jeu {@link ZombieDeBase}, {@link ZombieBlinde}
 */
public abstract class Zombie extends Entite {

	/** Le délai avant apparition du 1er <b>Zombie</b> dans le jeu */
	public static Timer delaiDebutPartie = new Timer(20_000); // 20 secondes
	/** Vitesse de déplacement en cases par seconde * 2 */
	private double vitesseDeplacementX;
	/** degats infliges par seconde */
	private double pointsDeDegats;
	private boolean peutAvancer;
	private boolean infligeDegats;
	private Timer timerDegats;
	private boolean obstacle;

	public Zombie(double x, double y) {
		super(x, y);
		// un zombie avance de 1/4 de cases par seconde (le jeu fait 9 cases de long)
		// la boucle du jeu s'execute toutes les 20ms
		// largeur du jeu = 900
		// 900 / 9 = 100 (9 cases)
		// 100 / 4 = 25 (1/4 de cases)
		// 25 / 50 = 0.5
		this.vitesseDeplacementX = 0.5;
		this.pointsDeDegats = 30;
		this.peutAvancer = true;
		this.infligeDegats = false;
	}

	public double getVitesseDeplacementX() {
		return vitesseDeplacementX;
	}

	/**
	 * À faire en cas de collision <br> Si un <b>Zombie</b> est en contact avec une {@link Plant}, il
	 * ne peut plus avancer et frappe la plante, lui infligeant des dégats
	 */
	public void collision() {
		this.obstacle = false;
		for (Entite e : GameWorld.entites) {
			if (e instanceof Plant) {
				if ((this.position.getX() - (this.getTailleX() / 3)) <= (e.position.getX() + (e.getTailleX() / 2))
						&& GameWorld.getNumeroLigne(this.getY()) == GameWorld.getNumeroLigne(e.getY())) {
					this.infligeDegats = true;
					this.peutAvancer = false;
					this.obstacle= true;
					this.attaque(e);
				}
			}
		}
		if(!this.obstacle) {
			this.peutAvancer = true;
		}
	}

	/**
	 * Enlève les points de vie adéquats à l'entite qui est attaquée
	 * @param e l'entite à attaquer
	 */
	public void attaque(Entite e) {
		if (this.infligeDegats && (this.timerDegats == null || this.timerDegats.hasFinished())) {
			this.timerDegats = new Timer(1_000);
			e.setPointsDeVie((int) (e.getPointsDeVie() - this.pointsDeDegats));
			if (e.getPointsDeVie() <= 0) {
				this.peutAvancer = true;
			}
		}
	}

	public boolean getPeutAvancer() {
		return peutAvancer;
	}

}
