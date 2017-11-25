import java.util.Random;

public class Jeu {

	int longueurNombreMystere = 4;
	int nombreUtilises[] = {0,1,2,3,4};
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
		
		String nombreMystereString;
		long maximumPossible;
		String maximumPossibleString;
		
		maximumPossible = nombreUtilises.length-1;//On récupère le nombre maximum à être utilisé dans nombreUtilises
		maximumPossibleString = ""+maximumPossible+maximumPossible+maximumPossible+maximumPossible+maximumPossible+maximumPossible+maximumPossible+maximumPossible+maximumPossible+maximumPossible;/*On l'ajoute
		le plus de fois possible à un String
		*/
		maximumPossible = Long.parseLong(maximumPossibleString);//On remet les valeurs numériques du String dans le Long maximumPossible
		
		Random random = new Random();
		nombreMystere = 0000000000 + (long)(random.nextDouble()*(maximumPossible - 0000000000L));//On génère un nombre aléatoire qui va jusqu'au maximum possible
		nombreMystereString = String.valueOf(nombreMystere);//On le convertit en String
		nombreMystere = Long.parseLong((nombreMystereString).substring(0,longueurNombreMystere));//On le reconvertit en Long en ne gardant que le nombre de caractères correspondant à longueurNombreMystere

	}
	
}
