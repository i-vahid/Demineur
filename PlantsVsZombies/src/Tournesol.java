/**
 * Produit un {@link Soleil} à intervalle régulier
 */
public class Tournesol extends Plant {

	/** Temps de production d'un soleil en secondes */
	private int tempsProductionSoleil;
	/** Pour gérer le temps entre 2 soleils produits */
	private Timer timerProductionSoleil;
	/** le cout en soleils d'un <b>Tournesol</b> */
	public static int coutSoleil = 50;
	/** timer de recharge pour pouvoir replanter un <b>Tournesol</b> */
	public static Timer timerRechargeAchat;
	public static int tempsRechargeAchat;

	public Tournesol(double x, double y) {
		super(x, y);
		this.setPointsDeVie(300);
		this.setTempsProductionSoleil(24);
		this.setImg("images/ajoutees/tournesol.png");
		this.setTailleX(90);
		this.setTailleY(90);
		this.setTimerProductionSoleil(new Timer(24_000));
		Tournesol.tempsRechargeAchat = 5_000;
	}

	public int getTempsProductionSoleil() {
		return tempsProductionSoleil;
	}

	public void setTempsProductionSoleil(int tempsProductionSoleil) {
		this.tempsProductionSoleil = tempsProductionSoleil;
	}

	public Timer getTimerProductionSoleil() {
		return timerProductionSoleil;
	}

	public void setTimerProductionSoleil(Timer timerProductionSoleil) {
		this.timerProductionSoleil = timerProductionSoleil;
	}

	/**
	 * Restart le timer de recharge de la classe <br>
	 * Utile pour ne pas pouvoir replanter une plante si son temps de rechrarge
	 * n'est pas écoulé
	 */
	public void restartTimerRecharge() {
		Tournesol.timerRechargeAchat = new Timer(Tournesol.tempsRechargeAchat);
	}

}
