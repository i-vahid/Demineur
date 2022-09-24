package demineur;

//Ceci est un squelette � REMPLIR pour le projet INF1 sur le jeu de d�mineur
//
//- N'oubliez pas de renseigner vos deux noms
//** El Mellali Yasser Groupe4-B �l�ve 1/2
//** VAHID TOURANG GROUP 4-B ** : �l�ve 2/2
//
//- Pour chaque question, le squelette donne le nom de la fonction � �crire mais *pas* la signature
//il faut remplir les types d'entr�es et de sorties (indiqu�s par ?) et remplir l'int�rieur du code de chaque fonction.
//
//- L'unique fichier de code que vous soumettrez sera ce fichier Java, donc n'h�sitez pas � le commenter abondamment.
//inutile d'exporter votre projet comme archive Zip et de rendre ce zip.
//Optionnel : vous pouvez aussi joindre un document PDF donnant des explications suppl�mentaires (si vous utilisez OpenOffice/LibreOffice/Word, exportez le document en PDF), avec �ventuellement des captures d'�cran montrant des �tapes affich�es dans la console
//
//- Regardez en ligne sur le Moodle pour le reste des consignes, et dans le fichier PDF du sujet du projet
//https://foad.univ-rennes1.fr/mod/assign/view.php?id=534254
//
//- A rendre avant le vendredi 04 d�cembre, maximum 23h59.
//
//- ATTENTION Le projet est assez long, ne commencez pas au dernier moment !
//
//- Enfin, n'h�sitez pas � contacter l'�quipe p�dagogique, en posant une question sur le forum du Moodle, si quelque chose n'est pas clair.
//

//Pour utiliser des scanners pour lire des entr�es depuis le clavier
//utilis�s en questions 4.d] pour la fonction jeu()
import java.util.Scanner;

//Pour la fonction entierAleatoire(a, b) que l'on vous donne ci-dessous
import java.util.concurrent.ThreadLocalRandom;

//L'unique classe de votre projet
public class Demineur {
	
	// Donn�, utile pour la question 1.b]
	public static int entierAleatoire(int a, int b){
		// Renvoie un entier al�atoire uniforme entre a (inclus) et b (inclus).
		return ThreadLocalRandom.current().nextInt(a, b + 1);
	}

	//
	// Exercice 1 : Initialisation des tableaux
	//

	// Question 1.a] d�clarez les variables globales T et Tadj ici
	static int[][] T;//Voici les variables globale
	static int[][] Tadj;
	int mine=1;
	// Question 1.b] Fonction init
	public static void init(int h,int l,int n) { //   initialiser les tableaux T et Tadj (h=hauteur,l=longeur,n=nombre de mines
		int mine = n;
		T=new int[h][l];
		Tadj=new int[h][l];
		for (int i=0;i<mine;) {    // on met les n mines dans les cas entierAleatoire(x,y) | 0<x<hauteur-1,0<y<longeur-1
			int x=entierAleatoire(0,h-1);
			int y=entierAleatoire(0,l-1) ;
			if(caseCorrecte(x,y) && Tadj[x][y] !=-1 ) {  // pour éviter la répétition de la coordonnée dans le tableau
				Tadj[x][y]=-1;
				i++;
			}
		}
	}

	// Question 1.c] Fonction caseCorrecte
	public static boolean caseCorrecte(int i,int j) { // on verifie si le case(i,j) est un case de tableau
		if(i>=0 && i<T.length && j>=0 && j<T[i].length) {		// il faut que 'i' soit 0<i<hauteur et j    0<j<longeur  
			return true;		// si c'est true , le case est correct
		}
		return false;			
	}

	// Question 1.d] Fonction calculerAdjacent
	public static void calculerAdjacent() {	
		for(int i=0;i<Tadj.length;i++) {
			for(int j=0;j<Tadj[i].length;j++) {
				if(Tadj[i][j]==0) {
					for(int x=-1;x<=1;x++) {
						for(int y=-1;y<=1;y++) {
							if(caseCorrecte(x+i,y+j)==true && Tadj[x+i][y+j]==-1) { // on verifie tous les cases adjecent(8cases) au tour de case (i,j)
								Tadj[i][j]++;		// si il y a une case de mine(-1) , on ajout +1 a case (i,j)
							}
						}
					}
				}
			}
		}
	}

	//
	// Exercice 2 : Affichage de la grille
	//

	// Question 2.a]


	public static void afficherGrille(boolean affMines) { // pour affichage l'etat de la jeu

		int l1=65;		// == 'A'
		int l2=97;		// == 'a'
		int h=0;
		boolean espace=false;		// pour verifier la condition pour avoir l'espace au debut de premier ligne
		boolean nombre=false;		// pour avoir les numero de ligne au debut de chaque collone .
	
		for(int i=0;i<T.length;i++) {
			
			nombre=false;
			
			if(!espace) { 				// pour mettre l'espase de premier ligne avant 'A' 
				System.out.print("  |");
				for(int f=0;f<T[i].length;f++) {	// afficher  A  -  Z 
					if(l1<=90) {							
						System.out.print((char)(l1) + "|");
						l1++;
					}
					else {							// afficher a - z
						System.out.print((char)(l2) + "|");
						l2++;
					}
				}
				System.out.println();
				espace=true;						// on ne met plus d'espace en premier ligne
			}
			for(int j=0;j<T[i].length;j++) {
				if(!nombre) {						// pour afficher les nombre debut de chaque ligne
					if(h<10) {
						System.out.print("0"+i+"|");
						h++;
					}
					else { 
						System.out.print(i+"|");
					}
					nombre=true;					// pour eviter l'affichage le nombre de ligne dans les autre collones
				}
				
				if(affMines) {						// si affmin==true  ==> le joue est fini . on affiche tout les case de mine
					if(T[i][j]==2) {				// et etat de la jeu
						T[i][j]=0;					// si T[i][j] == 2 ==>  le case est marque comme un drapeau 
					}
					if(Tadj[i][j]==-1 && T[i][j]!=2) {
						System.out.print("!|");
					}

					else if(T[i][j]==0){
						System.out.print(" |");	
					}
				}
				
				if(T[i][j]==0) {
					if(affMines==false) {		// si affmines==false , la jeu se continue  donc nous affichons l'etat de jeu
					
						System.out.print(" |");
					
					}
				}
				
				if(T[i][j]==1 && Tadj[i][j]!=-1) {		// T[i][j] ==1 on va reveler le cas si ce n'est pas case de mine
					System.out.print(Tadj[i][j]+"|");
				}
				
				if(T[i][j]==2) {			// on met drapeau 
					System.out.print("X|");
				}
				
				
			}
			System.out.println();
		}
	} 
		// Note : Dans un premier temps, consid�rer que la grille contiendra au plus 52 colonnes
		// (une pour chaque lettre de l'alphabet en majuscule et minuscule) et au plus 100 lignes
		// (entiers de 0 � 99).
	


	//
	// Exercice 3 : R�v�ler une case
	//

	// Question 3.a]
	public static boolean caseAdjacenteZero(int i,int j) { //Cette fonction renvoye true quand au moins une case adjacente a la case de coordonnees (i, j) est revelee et n’a aucune mine adjacente
		if(caseCorrecte(i,j)) { 
			for(int x=-1;x<=1;x++) {
				for(int y=-1;y<=1;y++) {
					if(x==0 && y==0) {
						y++;
					}
					if(caseCorrecte(x+i,y+j) && T[x+i][y+j]==1 && Tadj[x+i][y+j]==0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// Question 3.b]
	public static void revelation(int i,int j) { //
		if(Tadj[i][j]!=-1) {
			T[i][j]=1;
		}
		if (Tadj[i][j]==0){
			for(int x=-1;x<=1;x++) {
				for(int y=-1;y<=1;y++) {
					if((caseCorrecte(x+i,y+j) && Tadj[x+i][y+j]!=-1)) {
						T[x+i][y+j]=1;
						if(Tadj[x+i][y+j]==0) {
							revelation2(x+i,y+j);
						}
					}
				}
			}
		}
		
	}
	

	// Question 3.c] Optionnel
	public static void revelation2(int i,int j) { //
		if(Tadj[i][j]!=-1) {
			T[i][j]=1;
		}
		if (Tadj[i][j]==0){
			for(int x=-1;x<=1;x++) {
				for(int y=-1;y<=1;y++) {
					if((caseCorrecte(x+i,y+j) && Tadj[x+i][y+j]!=-1)) {
						T[x+i][y+j]=1;
						}
					}
				}
			}
		}

	// Question 3.d]
	public static void actionDrapeau(int i,int j) { 
		
		if(caseCorrecte(i,j)) {  //nous vérifions si le cas est correct
			
			if(T[i][j]!=2) { //si oui et que la variable T[i][j] n'est pas égale à 2 alors on la rend égale à 2
				
			T[i][j]=2;  //ajoute du drapeau
			
			} 
			else {
				if(T[i][j]==2) { //si à la place T[i][j] était déjà = 2 alors nous le rendons égal à 0
			
				
				T[i][j]=0;	//enleve du drapeau
				}
			}	
		}	
	}
	
	
	// Question 3.e]
	public static boolean revelerCase(int i,int j) { 
		if(caseCorrecte(i,j)) {		
			if(Tadj[i][j]==-1) {  //si Tadj[i][j] qui que l'utilisateur veut révéler est une mine alors on retourne false
				return false;
			}
			else {
				revelation(i,j); //si au contraire Tadj [i] [j] n'est pas une mine on révèle le cas et nous retournons true
				return true;   
			}
		}
		return false; //si les variables i et j ce ne sont pas des coordonnées de la table donc on retourne directement false
	}


	//
	// Exercice 4 : Boucle de jeu
	//

	// Question 4.a]
	public static boolean aGagne()  { //cette fonction ne renvoie true que si vous avez gagné
		for(int i=0;i<T.length;i++) { 
			for(int j=0;j<T[i].length;j++) {	
				if(Tadj[i][j]!=-1 && T[i][j]!=1){  //si Tadj [i] [j] n'est pas une mine et que T [i] [j] n'est pas révélé on retourne false
					return false;
				}
			}
		}
		return true; //nous retournons true seulement si tous les cas qui ne sont pas les miens sont révélés
	}

	// Question 4.b]
	public static boolean verifierFormat(String s) {
		
		if(s.length()!=4) {  //si la longueur de la String s dépasse 4 alors nous retournons false
			return false;
		}
		else {
			
			 if((s.charAt(0)=='r')||(s.charAt(0)=='d')) {  
				 
				 if( ((s.charAt(1)>='0')&&(s.charAt(1)<='9')) && ((s.charAt(2)>='0')&&(s.charAt(2)<='9')  && (s.charAt(3)>='A' && s.charAt(3)<='z')) ){ 
					
					 return true; //si le premier caractère est egal a 'r' ou 'd' alors et si les deuxième et troisième caractères sont compris entre '0' et '9' et le dernier caractère entre 'A' et 'z', nous retournons true
				 
				 }
			 }
		}
		return false;
		
	}

	// Question 4.c]
			
	public static int[] conversionCoordonnees(String input) { //cette fonction renvoie un tableau d'entiers indiquant les coordonnées choisies par l'utilisateur
		int[] tab=new int[3];       
		if(verifierFormat(input)) {		 //si le format de input est correct, la fonction verifierFormat retournera true
			if(input.charAt(0)=='r') { 
				tab[2]=1; //le troisieme element du tab vaut 1 si la joueuse a demande a reveler la case
			}
			if(input.charAt(0)=='d') {
				tab[2]=0; //le troisieme element du tab vaut 0 si la joueuse a demande a marquer la case
			}
			
			char col = input.charAt(3); //on prend le dernier caractère de la String input pour trouver l'équivalent en coordonnée de colonne
			if ( (col)<='Z' ) {
			col = (char) (col - 'A');
				tab[1] =(int)(col); 
			}
			if ( (col)>='a' ) {
				col = (char) (col - 'a');
					tab[1] =(int)(col) + 26;
				}
			
			tab[0]=Integer.parseInt(input.substring(1,3)); //Integer.parseInt (1,3) nous permet de prendre des caractères de la position 1 à la position 2 de la String input
			
		}
			
			return tab;
	
	}

	// Question 4.d]
	public static void jeu() {

		boolean jouer=true;     //variable utilisée pour savoir si le joueur joue toujours ou s'il a perdu
		
		Scanner sc=new Scanner(System.in);
		
		String cord; //variable qui contiendra les coordonnées données par le joueur
		
		while(jouer) {         
			
			afficherGrille(false);
			
			do {
				
				System.out.println(" le format doit être comme r00A (reveler le case) ou d00A drapeau le case");	
			
				cord=sc.nextLine();
			
			}while(!verifierFormat(cord));  //on laisse le joueur mettre les coordonnées, si elles ne respectent pas le format imposé alors il lui sera demandé de renvoyer les coordonnées
		
			int[] conversioncord=conversionCoordonnees(cord); //conversion de coordonnées
			
			if(caseCorrecte(conversioncord[0],conversioncord[1]))
			{
				if(conversioncord[2]==0) {  //si le joueur a demande de marquer le case
				
					actionDrapeau(conversioncord[0],conversioncord[1]);
				
				}else {
				
					if(Tadj[conversioncord[0]][conversioncord[1]]==-1) {//si le joueur a choisi des coordonnées contenant une mine
					
						System.out.println("Perdu...");	//nous affichons au joueur qu'il a perdu
				
						jouer=false;  //pour arrêter la boucle while
				
						afficherGrille(true);  //nous montrons la table avec toutes les mines en vue
				
					}else {
					
						revelerCase(conversioncord[0],conversioncord[1]); //si le joueur a choisi des coordonnées ne contenant pas de mines, nous révélons ces cas
					
					}
				
				}
			}else {
				
				System.out.println("les coordonnées:"+cord+" sont fausses!"); //si les coordonnées ne sont pas correctes
				
			}
			if(aGagne()) {//si aGagne() est true
				
				System.out.println("Gagné !");//nous affichons le mot gagné
				
				jouer=false; //nous arrêtons la boucle while
				
			}
		
			
		}
		
	sc.close();	
	}

	// Question 4.e]
	
	
	// Votre *unique* m�thode main
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Joue de demineur");
		
		int h;
		
		int l;
		
		int m;
		
		do {
		System.out.println("enter h entre 1 et 99");
		
		h = sc.nextInt();
		
		System.out.println("enter l entre 1 et 52");
		
		l= sc.nextInt();
		
		System.out.println("enter n nombre de mines");
		
		m=sc.nextInt();
		
		}while(h<0||h>99||l<1||l>52||(m>(h*l))); //si le format de la table n'est pas respecté alors le joueur doit saisir des données valides
		
		init(h,l,m);   
		
		calculerAdjacent();

		jeu();
		
		sc.close();
		
	}
	//
	// Exercice 5 bonus challenge : Pour aller plus loin
	//

	// Question 5.a] Optionnel
	public static void aide() {
		
	}

	// Question 5.b] Optionnel
	public static void intelligenceArtificielle() {
		
	}

	// Question 5.c] Optionnel
	public static void statistiquesVictoiresIA() {
		
	}

}
