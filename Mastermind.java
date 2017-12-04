import java.util.Random;

public class Mastermind extends Jeu{
	
	public Mastermind() {
		super("\n*****MASTERMIND*****");
		genererNombreMystere();
	}

public void genererNombreMystere() {
		
		int maximumPossible;
		
		maximumPossible = nombreUtilises.length-1;//On récupère le nombre maximum à être utilisé dans nombreUtilises
		
		Random random = new Random();
		
		int chiffreNombreMystere[] = new int [longueurNombreMystere];
		
		for (int i = 0; i < longueurNombreMystere; i++) {//On génère un chiffre aléatoire jusqu'à atteindre la longueur définie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
			nombreMystere += chiffreNombreMystere[i];
		}
		
		System.out.println(nombreMystere);
	}
}
