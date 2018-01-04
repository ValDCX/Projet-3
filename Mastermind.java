import java.util.Random;
import java.util.HashSet;
import java.util.Iterator;

public class Mastermind extends Jeu{
	
	HashSet liste = new HashSet();
	
	public Mastermind() {
		super("\n*****MASTERMIND*****");
		genererListeSolutions();
		genererNombreMystere();
		
		if (liste.contains(nombreMystere))
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

}
