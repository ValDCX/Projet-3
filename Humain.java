import java.util.Scanner;

import org.apache.log4j.Logger;

public class Humain extends Joueur {
	private static Logger logger = Logger.getLogger(Main.class);

	public Humain() {

	}

	public void proposerNombre() {
		System.out.print("Proposition : ");
		do {
			Scanner sc = new Scanner(System.in);

			proposition = sc.nextLine();

			longueurProposition = String.valueOf(proposition).length();// longueurProposition est égal à la longueur (nombre
																																	// de chiffres) dans proposition

			if (!proposition.matches("[0-9]+") || longueurProposition != Jeu.longueurNombreMystere)
				Erreur.erreurNombre();

		} while (longueurProposition != Jeu.longueurNombreMystere || !proposition.matches("[0-9]+"));// Boucle tant que la
																																																	// proposition n'a pas
																																																	// le même nombre de
																																																	// chiffres que le
																																																	// paramètre
																																																	// longueurNombreMystere
	logger.info("Nombre proposé : "+proposition);
	}
}
