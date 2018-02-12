import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

public class Ordinateur extends Joueur {

	private static Logger logger = Logger.getLogger(Main.class);
	String tourPrecedent = "";

	public Ordinateur() {

	}

	public void proposerNombre() { // Méthode utilisée dans PlusOuMoins
		System.out.print("Proposition : ");

		if (Jeu.compteur == 1) {
			proposition = "";
			Random random = new Random();

			int chiffreNombreMystere[] = new int[Jeu.longueurNombreMystere];

			for (int i = 0; i < Jeu.longueurNombreMystere; i++) {// On génère un chiffre aléatoire jusqu'à atteindre la
																														// longueur définie dans longueurNombreMystere
				chiffreNombreMystere[i] = random.nextInt(9 + 1);

				proposition += chiffreNombreMystere[i];
			}
			tourPrecedent = proposition;
			System.out.println(proposition);
		} else {
			int chiffreNombreMystere[] = new int[Jeu.longueurNombreMystere];

			for (int i = 0; i < Jeu.longueurNombreMystere; i++) {
				if (resultatPrecedent.charAt(i) == '+') {
					chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					chiffreNombreMystere[i]++;
				} else if (resultatPrecedent.charAt(i) == '-') {
					chiffreNombreMystere[i] = Character.getNumericValue(tourPrecedent.charAt(i));
					chiffreNombreMystere[i]--;
				} else if (resultatPrecedent.charAt(i) == '=')
					chiffreNombreMystere[i] = Character.getNumericValue(Jeu.nombreMystere.charAt(i));

				proposition += chiffreNombreMystere[i];
			}
			proposition = proposition.substring(Jeu.longueurNombreMystere, proposition.length());
			tourPrecedent = proposition;
			System.out.println(proposition);
		}
		logger.info("Nombre proposé : "+proposition);
	}

	public void piocherDansListe(ArrayList liste) { // Méthode utilisée dans Mastermind
		Random random = new Random();
		int index = random.nextInt(liste.size());
		proposition = (String) liste.get(index);
		logger.info("Nombre proposé parmi la liste de solutions : "+proposition);
	}
}
