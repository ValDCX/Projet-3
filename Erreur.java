
public abstract class Erreur {

		//Message affich� si une entr�e clavier ne correspond � aucun choix propos� 
	public static void erreurChoix() {
		System.out.println("\nVeuillez choisir parmi les propositions.");
	}
	
	public static void erreurNombre() {
		System.out.println("\nVeuillez entrer un nombre � "+Jeu.longueurNombreMystere+" chiffres.");
	}
}
