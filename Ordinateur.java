import java.util.Random;

public class Ordinateur extends Joueur{

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
			System.out.println(proposition);
		}
		else {			
			int chiffreNombreMystere[] = new int [Jeu.longueurNombreMystere];
			
			for (int i = 0; i < Jeu.longueurNombreMystere; i++) {
				if (Jeu.resultat.charAt(i) == '+') {
					chiffreNombreMystere[i] = Character.getNumericValue(proposition.charAt(i));
					chiffreNombreMystere[i]++;
				}
				else if (Jeu.resultat.charAt(i) == '-') {
					chiffreNombreMystere[i] = Character.getNumericValue(proposition.charAt(i));
					chiffreNombreMystere[i]--;
				}
				else if (Jeu.resultat.charAt(i) == '=')
					chiffreNombreMystere[i] = Character.getNumericValue(Jeu.nombreMystere.charAt(i));
				
				proposition += chiffreNombreMystere[i];		
			}
			proposition = proposition.substring(Jeu.longueurNombreMystere, proposition.length());
			System.out.println(proposition);
		}
	}
}
