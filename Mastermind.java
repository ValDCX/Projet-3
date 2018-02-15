import java.util.*;

import org.apache.log4j.Logger;

public class Mastermind extends Jeu {

	private static Logger logger = Logger.getLogger(Main.class);
	
	HashSet liste; // Set qui contiendra toutes les solutions possibles sans doublon
	byte bienPlaces = 0;
	byte presents = 0;
	int resultatOK = 0;
	int resultatMP = 0;
	ArrayList aListe; // ArrayList identique au set liste, mais plus modulable pour supprimer les
										// solutions erron�es au cours de la partie

	public Mastermind() {
		super("\n*****MASTERMIND*****");
		logger.info("Mastermind lanc�");
		longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurMastermind"));
		coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
		logger.info("Longueur du nombre myst�re et nombre de coups max autoris� r�cup�r�s dans config.properties");
	}

	public void challenger() {
		logger.info("Mode challenger lanc�");
		System.out.println("********MODE CHALLENGER********");
		initCompteur();
		genererListeSolutions();
		genererNombreMystere();
		joueur1 = new Humain();
		logger.info("Joueur 1 d�fini comme humain");
		// On boucle tant que le nombre myst�re n'est pas trouv�
		do {
			devMode();
			resetIndices();
			afficherCompteur();
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			afficherResultat();
			compteur++;
		} while (bienPlaces < nombreMystere.length()&& compteur < coupsMax + 1);
		finPartie("Vous avez");
	}

	public void defenseur() {
		logger.info("Mode d�fenseur lanc�");
		System.out.println("********MODE DEFENSEUR********");
		initCompteur();
		genererListeSolutions();
		joueur1 = new Humain();
		logger.info("Joueur 1 d�fini comme humain");
		joueur2 = new Ordinateur();
		logger.info("Joueur 2 d�fini comme ordinateur");
		joueur1.proposerNombre();
		nombreMystere = Joueur.proposition;
		System.out.println("Le nombre myst�re est " + nombreMystere);
		do {
			devMode();
			resetIndices();
			afficherCompteur();
			joueur2.piocherDansListe(aListe);
			System.out.println(Joueur.proposition);
			comparerNombres(joueur2);
			afficherResultat();
			enregistrerResultat();
			clean();
			compteur++;
		} while (resultatOK < nombreMystere.length()&& compteur < coupsMax + 1);
		finPartie("L'ordinateur a");
	}

	public void duel() {
		logger.info("Mode duel lanc�");
		System.out.println("********MODE DUEL********");
		String combinaisonJoueur1, combinaisonJoueur2;
		initCompteur();
		genererListeSolutions();
		genererNombreMystere();
		combinaisonJoueur1 = nombreMystere;// On stocke le nombre � deviner par le joueur ici
		joueur1 = new Humain();
		logger.info("Joueur 1 d�fini comme humain");
		joueur1.proposerNombre();// L'utilisateur entre la combinaison � deviner pour l'ordinateur
		combinaisonJoueur2 = Joueur.proposition;// Cette combinaison est stock�e ici
		joueur2 = new Ordinateur();
		logger.info("Joueur 2 d�fini comme ordinateur");

		do {
			resetIndices();
			nombreMystere = combinaisonJoueur1;
			devMode();
			System.out.println("� vous :");
			afficherCompteur();
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			afficherResultat();
			if (String.valueOf(Joueur.proposition).equals(nombreMystere)) {
				compteur++;
				break;
			}

			nombreMystere = combinaisonJoueur2;
			devMode();
			System.out.println("� l'ordinateur :");
			resetIndices();
			afficherCompteur();
			joueur2.piocherDansListe(aListe);
			System.out.println(Joueur.proposition);
			comparerNombres(joueur2);
			afficherResultat();
			enregistrerResultat();
			clean();
			compteur++;
		} while (!String.valueOf(Joueur.proposition).equals(nombreMystere)&& compteur < coupsMax + 1);
		if (nombreMystere == combinaisonJoueur1)
			finPartie("Vous avez");
		else if (nombreMystere == combinaisonJoueur2)
			finPartie("L'ordinateur a");
	}

	public void genererListeSolutions() {

		liste = new HashSet();
		int maximumPossible;

		maximumPossible = nombreUtilises.length - 1;// On r�cup�re le nombre maximum � �tre utilis� dans nombreUtilises

		Random random = new Random();

		int chiffreNombreMystere[] = new int[longueurNombreMystere];

		do {
			for (int i = 0; i < longueurNombreMystere; i++) {// On g�n�re un chiffre al�atoire jusqu'� atteindre la longueur
																												// d�finie dans longueurNombreMystere
				chiffreNombreMystere[i] = random.nextInt(maximumPossible + 1);

				nombreMystere += chiffreNombreMystere[i];
			}
			liste.add(nombreMystere);
			nombreMystere = "";
		} while (liste.size() < Math.pow(nombreUtilises.length, longueurNombreMystere));// Tant que liste ne contient pas
																																										// toutes les solutions possibles
																																										// (nombre de chiffres puissance
																																										// longueur du nombre)

		aListe = new ArrayList(liste);
		logger.info("Liste des solutions possibles g�n�r�e");
	}

	public void comparerNombres(Joueur joueur) {

		String sProposition = String.valueOf(joueur.proposition);// On met la valeur de proposition dans un String

		for (int i = 0; i < longueurNombreMystere; i++) {
			int chiffrePropose = Character.getNumericValue(sProposition.charAt(i));
			int chiffreNombreMystere = Character.getNumericValue(nombreMystere.charAt(i));

			if (chiffrePropose == chiffreNombreMystere)
				bienPlaces++;
			else if (nombreMystere.contains(String.valueOf(chiffrePropose)))
				presents++;
			else
				;
		}
		logger.info("Comparaison de la proposition de "+joueur+" ("+joueur.proposition+") avec le nombre myst�re ("+nombreMystere+")");
	}

	public void afficherResultat() {
		if (presents >= nombreMystere.length())
			presents -= bienPlaces;
		if (presents < 0)
			presents = 0;
		if (bienPlaces == nombreMystere.length())
			presents = 0;
		System.out.println(presents + " pr�sents et " + bienPlaces + " bien plac�s.\n\n--------\n");
		logger.info(presents + " pr�sents et " +bienPlaces+ " bien plac�s");
	}

	public void genererNombreMystere() {
		Random random = new Random();
		int index = random.nextInt(aListe.size());
		nombreMystere = (String) aListe.get(index);
		logger.info("Nombre myst�re choisi parmi la liste de solutions");
	}

	public void resetIndices() {
		presents = 0;
		bienPlaces = 0;
		logger.info("Indices remis � z�ro");
	}

	public void clean() { // Cette m�thode �limine de aListe toutes les combinaisons qui ne correspondent pas � la proposition de l'ordinateur
		aListe.remove(Joueur.proposition);

		for (int i = 0; i < aListe.size(); i++) {// Tant que la liste compl�te n'est pas parcourue

			String sNombre = String.valueOf(aListe.get(i));

			for (int n = 0; n < longueurNombreMystere; n++) {
				int chiffreNombreMystere = Character.getNumericValue(nombreMystere.charAt(n));
				int chiffreSolutionPotentielle = Character.getNumericValue(sNombre.charAt(n));

				if (chiffreSolutionPotentielle == chiffreNombreMystere)
					bienPlaces++;
				else if (nombreMystere.contains(String.valueOf(chiffreSolutionPotentielle)))
					presents++;
				else
					;
			}
			if (bienPlaces <= resultatOK) {
				aListe.remove(aListe.get(i));
				resetIndices();
				if (!aListe.contains(nombreMystere))
					aListe.add(nombreMystere);
			}

			else
				resetIndices();
		}
		logger.info("Combinaisons erron�es retir�es de la liste de solutions");
	}

	public void enregistrerResultat() {
		resultatOK = bienPlaces;
		resultatMP = presents;
	}
}
