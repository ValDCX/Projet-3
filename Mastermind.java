import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Mastermind extends Jeu{
	
	HashSet liste = new HashSet();
	byte bienPlaces = 0;
	byte malPlaces = 0;
	ArrayList aListe;
	
	public Mastermind() {
		super("\n*****MASTERMIND*****");
		joueur1 = new Ordinateur();
		genererListeSolutions();
		aListe = new ArrayList(liste);
		genererNombreMystere();
		System.out.println(nombreMystere);
		joueur1.proposition = "12345";
		System.out.println("Proposition : "+joueur1.proposition);
		comparerNombres(joueur1);
		
		if (aListe.contains(nombreMystere))
			System.out.println("Nombre trouvé !");
		else
			System.out.println("Nombre inconnu !");
	}

public void genererListeSolutions() {
	
		int maximumPossible;
		
		maximumPossible = nombreUtilises.length-1;//On récupère le nombre maximum à être utilisé dans nombreUtilises
		
		Random random = new Random();
		
		int chiffreNombreMystere[] = new int [longueurNombreMystere];
		
		do {
			for (int i = 0; i < longueurNombreMystere; i++) {//On génère un chiffre aléatoire jusqu'à atteindre la longueur définie dans longueurNombreMystere
				chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
				nombreMystere += chiffreNombreMystere[i];
			}
			liste.add(nombreMystere);
			nombreMystere = "";
		} while (liste.size() < Math.pow(nombreUtilises.length, longueurNombreMystere));//Tant que liste ne contient pas toutes les solutions possibles (nombre de chiffres puissance longueur du nombre)
		
		System.out.println("Longueur de liste : "+liste.size());
	}

public void comparerNombres(Joueur joueur) {
	
	String sProposition = String.valueOf(joueur.proposition);//On met la valeur de proposition dans un String
	
	for (int i = 0; i < longueurNombreMystere; i++) {
		int chiffrePropose = Character.getNumericValue(sProposition.charAt(i));
		int chiffreNombreMystere = Character.getNumericValue(nombreMystere.charAt(i));
	
		if (chiffrePropose == chiffreNombreMystere)
			bienPlaces++;
		else if (nombreMystere.contains(String.valueOf(chiffrePropose)))
			malPlaces++;
		else;
	}
	//Comptabilise les chiffres présents et mal placés. ****************A VERIFIER /!\******************
	if (malPlaces >= nombreMystere.length())
	malPlaces -= bienPlaces;
	if (malPlaces < 0)
		malPlaces = 0;
	System.out.println(malPlaces+" mal placés et "+bienPlaces+" bien placés.");
}

public void genererNombreMystere(){
	Random random = new Random();
	int index = random.nextInt(aListe.size());
	nombreMystere = (String)aListe.get(index);
	}
}
