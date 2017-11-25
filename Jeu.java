import java.util.Random;

public class Jeu {

	int longueurNombreMystere = 6;
	int nombreUtilises[] = {0,1,2,3,4,5,6};
	long nombreMystere;
	
	public Jeu() {
		genererNombreMystere();
		System.out.println(nombreMystere);
	}
	
	//******Méthodes*******
	
	public void challenger() {
		
	}
	
	public void defenseur() {
		
	}
	
	public void duel() {
		
	}
	
	//*****Méthodes*****

	//Génération du nombre mystère
	public void genererNombreMystere() {
		
		int maximumPossible;
		
		maximumPossible = nombreUtilises.length-1;//On récupère le nombre maximum à être utilisé dans nombreUtilises
		
		Random random = new Random();
		
		int chiffreNombreMystere[] = new int [longueurNombreMystere];
		String sChiffre = "";
		
		for (int i = 0; i < longueurNombreMystere; i++) {//On génère un chiffre aléatoire jusqu'à atteindre la longueur définie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
			sChiffre += chiffreNombreMystere[i];//On ajoute le chiffre généré au String sChiffre
			System.out.println(sChiffre);
		}
		
		//Si le premier chiffre aléatoire est un zéro, il est remplacé par un autre chiffre aléatoire entre 1 et 9 (sinon il ne s'affichera pas)
		if (sChiffre.charAt(0) == '0') {
			int r = random.nextInt(9-1)+1;
			sChiffre = r+sChiffre.substring(1, sChiffre.length());
		}
		
		nombreMystere = Long.parseLong((sChiffre));//On convertit sChiffre en Long dans nombreMystere

	}
	
}
