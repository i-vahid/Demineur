/**
 * Modèle pour les {@link Entite} de type plante
 */
public abstract class Plant extends Entite {

	/** Le cout de la plante en soleils */
	public static int coutSoleil;
	/**
	 * temps de recharge en millisecondes pendant lequel on ne peut pas racheter de suite
	 * la plante
	 */
	//public static int tempsRechargeAchat;
//	/** timer de recharge pour pouvoir replanter un <b>TirePois</b> */
//	public static Timer timerRechargeAchat;

	public Plant(double x, double y) {
		super(x, y);
	}

	@Override
	public void step() {
		this.position.setX(this.position.getX());
	}

	@Override
	public void dessine() {
		if (this.getPointsDeVie() > 0) {
			StdDraw.picture(this.getX(), this.getY(), this.getImg(), this.getTailleX(), this.getTailleY());
		} else {
			GameWorld.entitesToRemove.add(this);
		}
	}
	
	/**
	 * Restart le timer de recharge de la classe <br>
	 * Utile pour ne pas pouvoir replanter une plante si son temps de rechrarge
	 * n'est pas écoulé
	 */
	public abstract void restartTimerRecharge();

}
