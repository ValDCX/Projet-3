
public class PlusOuMoins extends Jeu{

	public PlusOuMoins() {
		super("\n*****PLUS OU MOINS*****");
	}
	
	public void challenger() {
		System.out.println("********MODE CHALLENGER********");
		initCompteur();
		genererNombreMystere();
		joueur1 = new Humain();
		//On boucle tant que le nombre myst�re n'est pas trouv�
		do {
			afficherCompteur();
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			compteur++;
		} while (!String.valueOf(Joueur.proposition).equals(nombreMystere) && compteur < coupsMax+1);
		finPartie("Vous avez");
	}
	
	public void defenseur() {
		System.out.println("********MODE DEFENSEUR********");
		initCompteur();
		joueur1 = new Humain();
		joueur2 = new Ordinateur();
		
		joueur1.proposerNombre();//L'utilisateur entre la combinaison � deviner
		nombreMystere = Joueur.proposition;
		System.out.println("Le nombre myst�re est "+nombreMystere+".\n");
		do {
			afficherCompteur();
			joueur2.proposerNombre();
			comparerNombres(joueur2);
			joueur2.resultatPrecedent = resultat;
			compteur++;
		} while (!String.valueOf(Joueur.proposition).equals(nombreMystere) && compteur < coupsMax+1);
		finPartie("L'ordinateur a");
	}
	
	public void duel() {
		System.out.println("********MODE DUEL********");
		String combinaisonJoueur1, combinaisonJoueur2;
		initCompteur();
		genererNombreMystere();
		combinaisonJoueur1 = nombreMystere;//On stocke le nombre � deviner par le joueur dans une variable
		joueur1 = new Humain();
		joueur1.proposerNombre();//L'utilisateur entre la combinaison � deviner pour l'ordinateur
		combinaisonJoueur2 = Joueur.proposition;//Cette combinaison est stock�e ici
		joueur2 = new Ordinateur();
		
		do {
			nombreMystere = combinaisonJoueur1;
			afficherCompteur();
			System.out.println("� vous : ");
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			if (String.valueOf(Joueur.proposition).equals(nombreMystere)) {
				compteur++;
				break;
			}
			
			nombreMystere = combinaisonJoueur2;
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
