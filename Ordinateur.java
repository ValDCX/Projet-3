import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

public class Ordinateur extends Joueur {

	private static Logger logger = Logger.getLogger(Main.class);
	String tourPrecedent = "";

	public Ordinateur() {

	}

	public void proposerNombre() { // M�thode utilis�e dans PlusOuMoins
		System.out.print("Proposition : ");

		if (Jeu.compteur == 1) {
			proposition = "";
			Random random = new Random();

			int chiffreNombreMystere[] = new int[Jeu.longueurNombreMystere];

			for (int i = 0; i < Jeu.longueurNombreMystere; i++) {// On g�n�re un chiffre al�atoire jusqu'� atteindre la
																														// longueur d�finie dans longueurNombreMystere
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
		logger.info("Nombre propos� : "+proposition);
	}

	public void piocherDansListe(ArrayList liste) { // M�thode utilis�e dans Mastermind
		Random random = new Random();
		int index = random.nextInt(liste.size());
		proposition = (String) liste.get(index);
		logger.info("Nombre propos� parmi la liste de solutions : "+proposition);
	}
}
