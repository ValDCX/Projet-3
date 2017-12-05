
public class PlusOuMoins extends Jeu{

	public PlusOuMoins() {
		super("\n*****PLUS OU MOINS*****");
	}
	
	public void challenger() {
		System.out.println("********MODE CHALLENGER********");
		compteur = 1;
		genererNombreMystere();
		joueur1 = new Humain();
		//On boucle tant que le nombre mystère n'est pas trouvé
		do {
			System.out.println("Coup n°"+(compteur));
			joueur1.proposerNombre();
			comparerNombres(joueur1);
			compteur++;
		} while (!String.valueOf(joueur1.proposition).equals(nombreMystere) && compteur < coupsMax+1);
		finPartie();
	}
	
	public void defenseur() {
		System.out.println("********MODE DEFENSEUR********");
		int nb;
		compteur = 0;
		joueur1 = new Humain();
		joueur2 = new Ordinateur();
		joueur1.proposerNombre();//L'utilisateur entre la combinaison à deviner
		//A compléter
	}
	
	public void duel() {
		System.out.println("********MODE DUEL********");
		compteur = 0;
		genererNombreMystere();
		joueur1 = new Humain();
		joueur2 = new Ordinateur();
		//A compléter
	}
}
