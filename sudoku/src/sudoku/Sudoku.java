package sudoku;

public class Sudoku {
	/*
	 * 
	 * L2 - PO, TP n 2 
	 * Auteur : Vahid Tourang
	 * 
	 */
	static final int n = 3 ;		// taille des regions
	/*
	 * Terminologie
	 * 
	 * m est un plateau (de sudoku) si
	 * 	- m est un int [][] ne contenant que des entiers compris entre 0 et 9
	 * 	- m.length = n^2
	 *  - m[i].length = n^2 pour tous les i de 0 a n^2-1
	 *  
	 */

	static String enClair (int [][] m) {
		/*
		 * Prerequis : m est un plateau de sudoku
		 * Resultat : une chaine dont l'affichage permet de visualiser m
		 * 
		 */
		String r = "" ;		
		for (int i = 0; i < n*n ; i++) {
			for (int j = 0; j < n*n ; j++) {
				r = r + m[i][j] + " " ;
				if (j%n == n-1) {r = r + "  ";}
			}
			if (i%n == n-1) {r = r + "\n";}
			r = r + "\n";
		}		
		r = r + " " ;		
		return r ;
	} 
	
	static int [][] aPartirDe (String s) {
		/*
		 * Prerequis : s est une chaine contenant au moins n^4 chiffres decimaux
		 * Resultat : un plateau de sudoku initialise avec les n^4 premiers chiffres
		 * decimaux de s (les chiffres sont consideres comme ranges par lignes).
		 */
		int [][] m = new int [n*n][n*n] ;
		int k = 0 ;
		for (int i = 0; i < m.length ; i++) {
			for (int j = 0; j < m[i].length ; j++) {
				while ("0123456789".indexOf(s.charAt(k))==-1) {k++;}
				m[i][j] = (int) s.charAt(k) - (int) '0' ;
				k++ ;
			}			
		}
		return m ;
		
	} 
	
	static boolean presentLigne (int [][] m, int v, int i) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - i est compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la ligne i
		 * 
		 */
		for(int col=0;col<n*n;col++) {
			if(m[i][col]==v) return true;
		}
		return false ;
	} 
	
	static boolean presentColonne (int [][] m, int v, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - j est compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la colonne j
		 * 
		 */
		for(int ligne=0;ligne<n*n;ligne++) {
			if(m[ligne][j]==v) return true;
		}
		return false ; 
	} 
	
	static boolean presentRegion  (int [][] m, int v, int i, int j) {
		/*
		 * Prerequis :
		 *  - m est un plateau de sudoku
		 *  - v est compris entre 1 et n^2
		 *  - i et j sont compris entre 0 et n^2-1
		 * Resultat : dans m, v est present dans la region contenant la case <i, j>
		 * 
		 */
		int newi=i/n;
		int newj=j/n;
		for(int ligne=newi*n;ligne<newi*n+n;ligne++) {
			for(int col=newj*n;col<newj*n+n;col++) {
				if(m[ligne][col]==v) return true;
			}
		}
		return false ; 
	} 
	
    static boolean [] lesPossiblesEn (int [][] m, int i, int j) {
        /*
         * Prerequis :
         *  - m est un plateau de sudoku
         *  - i et j sont compris entre 0 et n^2-1
         *  - m[i][j] vaut 0
         * Resultat : un tableau r de longueur n^2+1 tel que, dans la tranche r[1..n^2]
         * r[v] indique si v peut etre place en <i, j>
         * 
         */
        boolean[] possible = new boolean[(n*n)+1];
        for(int ent=1;ent<possible.length;ent++) {
            possible[ent]= !(presentRegion(m,ent,i,j) || presentColonne (m,ent,j)
                            || presentLigne(m,ent,i));
        }
        return possible ;
     
         
    } 
	static String enClair (boolean[] t) {
		/*
		 * Prerequis : t.length != 0
		 * Resultat :
		 * une chaine contenant tous les indices i de la tranche [1..t.length-1] tels
		 * que t[i] soit vrai
		 */
		String r = "{" ;
		for (int i = 1; i < t.length; i++) {
			if (t[i]) {r = r + i + ", " ; }
		}
		if (r.length() != 1) {r = r.substring(0, r.length()-2);}
		return r + "}" ;
	} 
	
	static int toutSeul (int [][] m, int i, int j) {
        /*
         * Prerequis :
         *  - m est un plateau de sudoku
         *  - i et j sont compris entre 0 et n^2-1
         *  - m[i][j] vaut 0
         * Resultat :
         *  - v     si la seule valeur possible pour <i, j> est v
         *  - -1     dans les autres cas
         * 
         */
        boolean[] r = lesPossiblesEn (m,i,j);
        int cmpt = 0;
        int v = -1;
            for(int ind=1;ind<=n*n;ind++) {
                if (r[ind]==true) { cmpt++; v=ind;}    
            }
        if (cmpt==1)  return v;
        
        return -1 ; 
    } 
	
	static void essais (String grille) {
		/*
		 * Prerequis : grille represente une grille de sudoku
		 * (les chiffres sont consideres comme ranges par lignes)
		 * 
		 * Effet :
		 * 1) affiche en clair la grille
		 * 2) affecte, tant que faire se peut, toutes les cases pour lesquelles il n'y
		 *    a qu'une seule possibilite
		 * 3) affiche en clair le resultat final
		 */
		   int[][] m = aPartirDe(grille);
	        System.out.println("Probleme\n\n"+enClair(m));

	        
	        boolean mod= true;
	        
	        while(mod) {
	        
	        		mod=false ;
	            for(int i=0;i<n*n;i++) {
	                for(int j=0;j<n*n;j++) {
	                    if(m[i][j]==0) {
	                        int n= toutSeul(m,i,j);
	                        if(n!= -1) {
	                            m[i][j]=n;
	                            System.out.println("La case ["+i+","+j+"] a été modifié par la valeur: "+n);
	                            mod = true;  
	                    }
	                }
	                
	                
	            
	        }
	            
	    }  
	        }
	        System.out.println("\nIl se peut qu'on ait avance\n\n"+enClair(m));
	} 
	
	public static void main(String[] args) {
		String grille1 = 
			"040 001 006 \n" +
			"007 900 800 \n" +
			"190 086 074 \n" +
			"            \n" +
			"200 690 010 \n" +
			"030 405 090 \n" +
			"060 017 003 \n" +
			"            \n" +
			"910 750 042 \n" +
			"008 002 700 \n" +
			"400 300 080   " ;
		String grille2 = 
			"030 000 006 \n" +
			"000 702 300 \n" +
			"104 038 000 \n" +
			"            \n" +
			"300 020 810 \n" +
			"918 000 265 \n" +
			"062 050 007 \n" +
			"            \n" +
			"000 140 708 \n" +
			"001 209 000 \n" +
			"800 000 020   " ;
		
		essais(grille1) ;
	
		int[][] m=aPartirDe(grille1);
		System.out.println(presentRegion(m,1,0, 3));
		System.out.println(presentRegion(m,1,3, 0));
		System.out.println(presentRegion(m,1,0, 0));
		System.out.println(enClair(lesPossiblesEn(m,4,5)));
	
		essais(grille1);
		
	//	essais(grille2) ;
	}
		
}