/**
 * une <b>Noix</b> ralentit les {@link Zombie}. <br>
 * Elle ne tire pas et n'inflige pas de dégats mais est un obstacle. <br>
 * Le zombie rencontrant une noix ne peut pas passer tant qu'il ne l'a pas détruite 
 */
public class Noix extends Plant {
	/** Le cout d'une <b>Noix</b> en soleils */
	public static int coutSoleil = 50;
	/** timer de recharge pour pouvoir replanter une <b>Noix</b> */
	public static Timer timerRechargeAchat;
	public static int tempsRechargeAchat;

	public Noix(double x, double y) {
		super(x, y);
		this.setPointsDeVie(1500);
		Noix.tempsRechargeAchat = 20_000;
		this.setImg("images/ajoutees/noix.png");
		this.setTailleX(90);
		this.setTailleY(110);
	}
	
	/**
	 * Restart le timer de recharge de la classe <br>
	 * Utile pour ne pas pouvoir replanter une plante si son temps de rechrarge
	 * n'est pas écoulé
	 */
	public void restartTimerRecharge() {
		Noix.timerRechargeAchat = new Timer(Noix.tempsRechargeAchat);
	}

}
