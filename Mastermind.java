import java.util.*;

public class Mastermind extends Jeu {

	HashSet liste; // Set qui contiendra toutes les solutions possibles sans doublon
	byte bienPlaces = 0;
	byte presents = 0;
	int resultatOK = 0;
	int resultatMP = 0;
	ArrayList aListe; // ArrayList identique au set liste, mais plus modulable pour supprimer les
										// solutions erronées au cours de la partie
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
		// On boucle tant que le nombre mystère n'est pas trouvé
		do {
			resetIndices();
			afficherCompteur();
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			afficherResultat();
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
		System.out.println("Le nombre mystère est " + nombreMystere);
		do {
			resetIndices();
			afficherCompteur();
			joueur2.piocherDansListe(aListe);
			System.out.println(Joueur.proposition);
			comparerNombres(joueur2);
			afficherResultat();
			enregistrerResultat();
			clean();
			compteur++;
		} while (resultatOK < nombreMystere.length());
		finPartie("L'ordinateur a");
	}

	public void duel() {
		System.out.println("********MODE DUEL********");
	}

	public void genererListeSolutions() {

		liste = new HashSet();
		int maximumPossible;

		maximumPossible = nombreUtilises.length - 1;// On récupère le nombre maximum à être utilisé dans nombreUtilises

		Random random = new Random();

		int chiffreNombreMystere[] = new int[longueurNombreMystere];

		do {
			for (int i = 0; i < longueurNombreMystere; i++) {// On génère un chiffre aléatoire jusqu'à atteindre la longueur
																												// définie dans longueurNombreMystere
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
		
	}
	
	public void afficherResultat() {
		if (presents >= nombreMystere.length())
			presents -= bienPlaces;
		if (presents < 0)
			presents = 0;
		if (bienPlaces == nombreMystere.length())
			presents = 0;
		System.out.println(presents + " présents et " + bienPlaces + " bien placés.");
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
		
		for (int i = 0; i < aListe.size(); i++) {//Tant que la liste complète n'est pas parcourue
			
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
			if (bienPlaces < resultatOK) {
				aListe.remove(aListe.get(i));
				resetIndices();
			}
			else
				resetIndices();
		}
		System.out.println("Longueur de liste : "+aListe.size());
	}
	
	public void enregistrerResultat() {
		resultatOK = bienPlaces;
		resultatMP = presents;
	}
}
