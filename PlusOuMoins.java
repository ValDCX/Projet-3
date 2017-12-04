
public class PlusOuMoins extends Jeu{

	public PlusOuMoins() {
		super("\n*****PLUS OU MOINS*****");
	}
	
	public void challenger() {
		System.out.println("********MODE CHALLENGER********");
		compteur = 0;
		genererNombreMystere();
		joueur1 = new Humain();
		//On boucle tant que le nombre mystère n'est pas trouvé
		do {
			joueur1.proposerNombre();
			comparerNombres(joueur1);
		} while (!String.valueOf(joueur1.proposition).equals(nombreMystere));
		System.out.println("Bravo ! Vous avez trouvé le nombre mystère en "+compteur+" coups !");
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
