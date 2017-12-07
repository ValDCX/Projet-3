import java.util.Random;

public class Ordinateur extends Joueur{

	String tourPrecedent = "";
	
	public Ordinateur() {
		
	}
	
	public void proposerNombre() {
		System.out.print("Proposition : ");	
		
		if (Jeu.compteur == 1) {
			proposition = "";
			Random random = new Random();
			
			int chiffreNombreMystere[] = new int [Jeu.longueurNombreMystere];
			
			for (int i = 0; i < Jeu.longueurNombreMystere; i++) {//On génère un chiffre aléatoire jusqu'à atteindre la longueur définie dans longueurNombreMystere
				chiffreNombreMystere[i] = random.nextInt(9+1);
				
				proposition += chiffreNombreMystere[i];
			}
			tourPrecedent = proposition;
			System.out.println(proposition);
		}
		else {			
			int chiffreNombreMystere[] = new int [Jeu.longueurNombreMystere];
			
			for (int i = 0; i < Jeu.longueurNombreMystere; i++) {
				if (resultatPrecedent.charAt(i) == '+') {
					chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					chiffreNombreMystere[i]++;
				}
				else if (resultatPrecedent.charAt(i) == '-') {
					chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					chiffreNombreMystere[i]--;
				}
				else if (resultatPrecedent.charAt(i) == '=')
					chiffreNombreMystere[i] = Character.getNumericValue(Jeu.nombreMystere.charAt(i));
				
				proposition += chiffreNombreMystere[i];		
			}
			proposition = proposition.substring(Jeu.longueurNombreMystere, proposition.length());
			tourPrecedent = proposition;
			System.out.println(proposition);
		}
	}
}
