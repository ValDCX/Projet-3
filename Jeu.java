import java.util.Random;

public class Jeu {

	int longueurNombreMystere = 6;
	int nombreUtilises[] = {0,1,2,3,4,5,6};
	long nombreMystere;
	
	public Jeu() {
		genererNombreMystere();
		System.out.println(nombreMystere);
	}
	
	//******M�thodes*******
	
	public void challenger() {
		
	}
	
	public void defenseur() {
		
	}
	
	public void duel() {
		
	}
	
	//*****M�thodes*****

	//G�n�ration du nombre myst�re
	public void genererNombreMystere() {
		
		int maximumPossible;
		
		maximumPossible = nombreUtilises.length-1;//On r�cup�re le nombre maximum � �tre utilis� dans nombreUtilises
		
		Random random = new Random();
		
		int chiffreNombreMystere[] = new int [longueurNombreMystere];
		String sChiffre = "";
		
		for (int i = 0; i < longueurNombreMystere; i++) {//On g�n�re un chiffre al�atoire jusqu'� atteindre la longueur d�finie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
			sChiffre += chiffreNombreMystere[i];//On ajoute le chiffre g�n�r� au String sChiffre
			System.out.println(sChiffre);
		}
		
		//Si le premier chiffre al�atoire est un z�ro, il est remplac� par un autre chiffre al�atoire entre 1 et 9 (sinon il ne s'affichera pas)
		if (sChiffre.charAt(0) == '0') {
			int r = random.nextInt(9-1)+1;
			sChiffre = r+sChiffre.substring(1, sChiffre.length());
		}
		
		nombreMystere = Long.parseLong((sChiffre));//On convertit sChiffre en Long dans nombreMystere

	}
	
}
