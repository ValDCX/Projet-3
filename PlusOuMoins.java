import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;

public class PlusOuMoins extends Jeu {

	private static Logger logger = Logger.getLogger(Main.class);
	
	public PlusOuMoins() {
		
		super("\n*****PLUS OU MOINS*****");
		logger.info("Plus ou Moins lanc�");
		longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurPlusOuMoins"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		logger.info("Longueur du nombre myst�re et nombre de coups max autoris� r�cup�r�s dans config.properties");
	}

	public void challenger() {
		logger.info("Mode challenger lanc�");
		System.out.println("********MODE CHALLENGER********");
		initCompteur();
		genererNombreMystere();
		joueur1 = new Humain();
		logger.info("Joueur 1 d�fini comme humain");
		// On boucle tant que le nombre myst�re n'est pas trouv�
		do {
			devMode();
			afficherCompteur();
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			compteur++;
		} while (!String.valueOf(Joueur.proposition).equals(nombreMystere) && compteur < coupsMax + 1);
		finPartie("Vous avez");
	}

	public void defenseur() {
		logger.info("Mode d�fenseur lanc�");
		System.out.println("********MODE DEFENSEUR********");
		initCompteur();
		joueur1 = new Humain();
		logger.info("Joueur 1 d�fini comme humain");
		joueur2 = new Ordinateur();
		logger.info("Joueur 2 d�fini comme ordinateur");

		joueur1.proposerNombre();// L'utilisateur entre la combinaison � deviner
		nombreMystere = Joueur.proposition;
		System.out.println("Le nombre myst�re est " + nombreMystere + ".\n");
		do {
			devMode();
			afficherCompteur();
			joueur2.proposerNombre();
			comparerNombres(joueur2);
			joueur2.resultatPrecedent = resultat;
			compteur++;
		} while (!String.valueOf(Joueur.proposition).equals(nombreMystere) && compteur < coupsMax + 1);
		finPartie("L'ordinateur a");
	}

	public void duel() {
		logger.info("Mode duel lanc�");
		System.out.println("********MODE DUEL********");
		String combinaisonJoueur1, combinaisonJoueur2;
		initCompteur();
		genererNombreMystere();
		combinaisonJoueur1 = nombreMystere;// On stocke le nombre � deviner par le joueur dans une variable
		joueur1 = new Humain();
		logger.info("Joueur 1 d�fini comme huamin");
		joueur1.proposerNombre();// L'utilisateur entre la combinaison � deviner pour l'ordinateur
		combinaisonJoueur2 = Joueur.proposition;// Cette combinaison est stock�e ici
		joueur2 = new Ordinateur();
		logger.info("Joueur 2 d�fini comme ordinateur");

		do {
			nombreMystere = combinaisonJoueur1;
			devMode();
			afficherCompteur();
			System.out.println("� vous : ");
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			if (String.valueOf(Joueur.proposition).equals(nombreMystere)) {
				compteur++;
				break;
			}

			nombreMystere = combinaisonJoueur2;
			devMode();
			System.out.println("� l'ordinateur :");
			joueur2.proposerNombre();
			comparerNombres(joueur2);
			joueur2.resultatPrecedent = resultat;
			compteur++;
		} while (!String.valueOf(Joueur.proposition).equals(nombreMystere));
		if (nombreMystere == combinaisonJoueur1)
			finPartie("Vous avez");
		else if (nombreMystere == combinaisonJoueur2)
			finPartie("L'ordinateur a");
	}
}
