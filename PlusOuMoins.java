import java.io.*;
import java.util.*;

import org.apache.log4j.Logger;

public class PlusOuMoins extends Jeu {

	private static Logger logger = Logger.getLogger(Main.class);
	
	public PlusOuMoins() {
		
		super("\n*****PLUS OU MOINS*****");
		logger.info("Plus ou Moins lancé");
		longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurPlusOuMoins"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		logger.info("Longueur du nombre mystère et nombre de coups max autorisé récupérés dans config.properties");
	}

	public void challenger() {
		logger.info("Mode challenger lancé");
		System.out.println("********MODE CHALLENGER********");
		initCompteur();
		genererNombreMystere();
		joueur1 = new Humain();
		logger.info("Joueur 1 défini comme humain");
		// On boucle tant que le nombre mystère n'est pas trouvé
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
		logger.info("Mode défenseur lancé");
		System.out.println("********MODE DEFENSEUR********");
		initCompteur();
		joueur1 = new Humain();
		logger.info("Joueur 1 défini comme humain");
		joueur2 = new Ordinateur();
		logger.info("Joueur 2 défini comme ordinateur");

		joueur1.proposerNombre();// L'utilisateur entre la combinaison à deviner
		nombreMystere = Joueur.proposition;
		System.out.println("Le nombre mystère est " + nombreMystere + ".\n");
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
		logger.info("Mode duel lancé");
		System.out.println("********MODE DUEL********");
		String combinaisonJoueur1, combinaisonJoueur2;
		initCompteur();
		genererNombreMystere();
		combinaisonJoueur1 = nombreMystere;// On stocke le nombre à deviner par le joueur dans une variable
		joueur1 = new Humain();
		logger.info("Joueur 1 défini comme huamin");
		joueur1.proposerNombre();// L'utilisateur entre la combinaison à deviner pour l'ordinateur
		combinaisonJoueur2 = Joueur.proposition;// Cette combinaison est stockée ici
		joueur2 = new Ordinateur();
		logger.info("Joueur 2 défini comme ordinateur");

		do {
			nombreMystere = combinaisonJoueur1;
			devMode();
			afficherCompteur();
			System.out.println("À vous : ");
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			if (String.valueOf(Joueur.proposition).equals(nombreMystere)) {
				compteur++;
				break;
			}

			nombreMystere = combinaisonJoueur2;
			devMode();
			System.out.println("À l'ordinateur :");
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
