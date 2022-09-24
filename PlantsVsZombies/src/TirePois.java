/**
 * Représente un tire-pois <br>
 * Un <b>TirePois</b> tire des {@link Pois} à intervalle régulier lorsqu'un {@link Zombie} est sur la même ligne
 */
public class TirePois extends Plant {
	/** le temps en millisecondes à attendre entre 2 tirs de pois */
	private long intervalleTir;
	/** les degats infliges par un tir */
	private double pointsDeDegats;
	/** pour gérer l'intervalle de tir */
	private Timer timer;
	/** cout en soleils d'un <b>TirePois</b> */
	public static int coutSoleil = 100;
	/** timer de recharge pour pouvoir replanter un <b>TirePois</b> */
	public static Timer timerRechargeAchat;
	public static int tempsRechargeAchat;

	public TirePois(double x, double y) {
		super(x, y);
		this.setPointsDeVie(300);
		this.setIntervalleTir(1500);
		this.setPointsDeDegats(20);
		this.setImg("images/ajoutees/tire_pois.png");
		this.setTailleX(90);
		this.setTailleY(90);
		this.setTimer(this.intervalleTir);
		TirePois.tempsRechargeAchat = 5_000;
	}

	/**
	 * Permet au <b>TirePois</b> de tirer
	 * @return le nouveau {@link Pois} tiré
	 */
	public Pois tire() {
		return new Pois(this.getX() + (this.getTailleX() / 2), this.getY() + 15);
	}

	/**
	 * Permet de savoir si un <b>TirePois</b> peut tirer <br> 
	 * Un <b>TirePois</b> peut tirer s'il y a un {@link Zombie} sur sa ligne
	 * @return true ssi un <b>Zombie</b> est sur la même ligne que le tire pois
	 */
	public boolean peutTirer() {
		int numeroLigneTirePois = GameWorld.getNumeroLigne(this.getY());
		for (Integer i : GameWorld.lignesComprenantUnZombie) {
			if (i == numeroLigneTirePois && this.getTimer().hasFinished()) {
				this.timer.restart();
				return true;
			}
		}
		return false;
	}

	public double getIntervalleTir() {
		return intervalleTir;
	}

	public void setIntervalleTir(long intervalleTir) {
		this.intervalleTir = intervalleTir;
	}

	public double getPointsDeDegats() {
		return pointsDeDegats;
	}

	public void setPointsDeDegats(double pointsDeDegats) {
		this.pointsDeDegats = pointsDeDegats;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(long intervalleTir) {
		this.timer = new Timer(intervalleTir);
	}
	
	/**
	 * Restart le timer de recharge de la classe <br>
	 * Utile pour ne pas pouvoir replanter une plante si son temps de rechrarge
	 * n'est pas écoulé
	 */
	public void restartTimerRecharge() {
		TirePois.timerRechargeAchat = new Timer(TirePois.tempsRechargeAchat);
	}

}
