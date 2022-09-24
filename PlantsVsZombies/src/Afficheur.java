/**
 * L'afficheur sur lequel sont affichées les cartes des plantes disponibles dans
 * le jeu <br>
 * Un <b>Afficheur</b> est complété par un {@link CompteurSoleils}
 */
public class Afficheur extends Entite {

	/** La carte qui représente un {@link TirePois} */
	Carte carteTirePois;
	/** La carte qui représente un {@link Tournesol} */
	Carte carteTournesol;
	/** La carte qui représente une {@link Noix} */
	Carte carteNoix;

	/**
	 * Sert à représenter une carte dans un {@link Afficheur}
	 */
	public class Carte extends Entite {

		/** L'Entite qui est représentée par la carte. <i>Ex : 'p' pour <b>TirePois</b></i> */
		private char entiteRepresentee;
		/** Le chemin de l'image grisée qui sert à représenter une carte lorsque l'utilisateur n'a pas assez de soleils pour l'acheter */
		private String nomImageGrise;
		/** Le coût d'une plante représentée par la carte */
		private int coutSoleil;

		public Carte(double x, double y, String file, char entiteRepresentee) {
			super(x, y);
			this.setImg(file);
			this.setTailleX(45);
			this.setTailleY(67);
			this.entiteRepresentee = entiteRepresentee;
			String[] t = file.split("\\.");
			this.nomImageGrise = t[0] + "_grise." + t[1];
			if (this.entiteRepresentee == 'p') {
				this.coutSoleil = TirePois.coutSoleil;
			} else if (this.entiteRepresentee == 't') {
				this.coutSoleil = Tournesol.coutSoleil;
			} else if (this.entiteRepresentee == 'n') {
				this.coutSoleil = Noix.coutSoleil;
			}
		}

		@Override
		public void step() {
			this.position.setX(this.position.getX());
		}

		@Override
		public void dessine() {
			boolean timerRecharge = false;
			if (this.entiteRepresentee == 'p') {
				this.coutSoleil = TirePois.coutSoleil;
				timerRecharge = this.timerRechargeEntiteRepresentee('p');
			} else if (this.entiteRepresentee == 't') {
				this.coutSoleil = Tournesol.coutSoleil;
				timerRecharge = this.timerRechargeEntiteRepresentee('t');
			} else if (this.entiteRepresentee == 'n') {
				this.coutSoleil = Noix.coutSoleil;
				timerRecharge = this.timerRechargeEntiteRepresentee('n');
			}
			if (GameWorld.compteurSoleil.getValeur() >= this.coutSoleil && timerRecharge) {
				StdDraw.picture(this.getX(), this.getY(), this.getImg(), this.getTailleX(), this.getTailleY());
			} else {
				StdDraw.picture(this.getX(), this.getY(), this.nomImageGrise, this.getTailleX(), this.getTailleY());
			}
		}

		public char getEntiteRepresentee() {
			return this.entiteRepresentee;
		}

		public int getCoutSoleil() {
			return this.coutSoleil;
		}
		
		/**
		 * @param entiteRepresentee l'entité représentée par la carte
		 * @return true ssi le timer est nul ou terminé
		 */
		public boolean timerRechargeEntiteRepresentee(char entiteRepresentee) {
			if (this.entiteRepresentee == 'p') {
				return (TirePois.timerRechargeAchat == null || TirePois.timerRechargeAchat.hasFinished());
			} else if (this.entiteRepresentee == 't') {
				return (Tournesol.timerRechargeAchat == null || Tournesol.timerRechargeAchat.hasFinished());
			} else if (this.entiteRepresentee == 'n') {
				return (Noix.timerRechargeAchat == null || Noix.timerRechargeAchat.hasFinished());
			}
			return false;
		}
		
	}

	public Afficheur(double x, double y) {
		super(x, y);
		this.setImg("images/ajoutees/afficheur.jpg");
		this.setTailleX(900 / 2);
		this.setTailleY(151 / 2);
		this.carteTirePois = new Carte(this.getX() - (this.getTailleX() / 2) + 100, this.getY() + 1,
				"images/ajoutees/card_tire_pois.jpg", 'p');
		this.carteTournesol = new Carte(this.getX() - (this.getTailleX() / 2) + 155, this.getY() + 1,
				"images/ajoutees/card_tournesol.jpg", 't');
		this.carteNoix = new Carte(this.getX() - (this.getTailleX() / 2) + 210, this.getY() + 1,
				"images/ajoutees/card_noix.jpg", 'n');
	}

	@Override
	public void step() {
		this.position.setX(this.position.getX());
	}

	@Override
	public void dessine() {
		StdDraw.picture(this.getX(), this.getY(), this.getImg(), this.getTailleX(), this.getTailleY());
	}

}
