
public abstract class Erreur {

		//Message affiché si une entrée clavier ne correspond à aucun choix proposé 
	public static void erreurChoix() {
		System.out.println("\nVeuillez choisir parmi les propositions.");
	}
	
	public static void erreurNombre() {
		System.out.println("\nVeuillez entrer un nombre à "+Jeu.longueurNombreMystere+" chiffres.");
	}
}
