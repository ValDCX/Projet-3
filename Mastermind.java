import java.util.Random;

public class Mastermind extends Jeu{
	
	public Mastermind() {
		super("\n*****MASTERMIND*****");
		genererNombreMystere();
	}

public void genererNombreMystere() {
		
		int maximumPossible;
		
		maximumPossible = nombreUtilises.length-1;//On r�cup�re le nombre maximum � �tre utilis� dans nombreUtilises
		
		Random random = new Random();
		
		int chiffreNombreMystere[] = new int [longueurNombreMystere];
		
		for (int i = 0; i < longueurNombreMystere; i++) {//On g�n�re un chiffre al�atoire jusqu'� atteindre la longueur d�finie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
			nombreMystere += chiffreNombreMystere[i];
		}
		
		System.out.println(nombreMystere);
	}
}
