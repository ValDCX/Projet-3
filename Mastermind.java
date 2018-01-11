import java.util.*;

public class Mastermind extends Jeu {

	HashSet liste; // Set qui contiendra toutes les solutions possibles sans doublon
	byte bienPlaces = 0;
	byte presents = 0;
	ArrayList aListe; // ArrayList identique au set liste, mais plus modulable pour supprimer les
										// solutions erron�es au cours de la partie
	ListIterator iterator = aListe.listIterator();

	public Mastermind() {
		super("\n*****MASTERMIND*****");
	}

	public void challenger() {
		System.out.println("********MODE CHALLENGER********");
		initCompteur();
		genererListeSolutions();
		genererNombreMystere();
		System.out.println(nombreMystere);
		joueur1 = new Humain();
		// On boucle tant que le nombre myst�re n'est pas trouv�
		do {
			resetIndices();
			afficherCompteur();
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			compteur++;
		} while (bienPlaces < nombreMystere.length());
		finPartie("Vous avez");
	}

	public void defenseur() {
		System.out.println("********MODE DEFENSEUR********");
		initCompteur();
		genererListeSolutions();
		joueur1 = new Humain();
		joueur2 = new Ordinateur();
		joueur1.proposerNombre();
		nombreMystere = Joueur.proposition;
		System.out.println("Le nombre myst�re est " + nombreMystere);
		do {
			resetIndices();
			afficherCompteur();
			joueur2.piocherDansListe(aListe);
			System.out.println(Joueur.proposition);
			comparerNombres(joueur2);
			clean();
			System.out.println("Longueur de liste : " + aListe.size());
			compteur++;
		} while (bienPlaces < nombreMystere.length());
		finPartie("L'ordinateur a");
	}

	public void duel() {
		System.out.println("********MODE DUEL********");
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
		System.out.println("Longueur de liste : " + liste.size());
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
		// Comptabilise les chiffres pr�sents et mal plac�s. ****************A VERIFIER
		// /!\******************
		if (presents >= nombreMystere.length())
			presents -= bienPlaces;
		if (presents < 0)
			presents = 0;
		if (bienPlaces == nombreMystere.length())
			presents = 0;
		System.out.println(presents + " pr�sents et " + bienPlaces + " bien plac�s.");
	}

	public void genererNombreMystere() {
		Random random = new Random();
		int index = random.nextInt(aListe.size());
		nombreMystere = (String) aListe.get(index);
	}

	public void resetIndices() {
		presents = 0;
		bienPlaces = 0;
	}

	public void clean() {
		aListe.remove(Joueur.proposition);
	}
}
