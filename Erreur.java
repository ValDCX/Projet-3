import org.apache.log4j.Logger;

public abstract class Erreur {
	
	private static Logger logger = Logger.getLogger(Main.class);

	// Message affich� si une entr�e clavier ne correspond � aucun choix propos�
	public static void erreurChoix() {
		System.out.println("\nVeuillez choisir parmi les propositions.");
		logger.error("Le choix entr� n'existe pas parmi les propositions");
	}

	public static void erreurNombre() {
		System.out.println("\nVeuillez entrer un nombre � " + Jeu.longueurNombreMystere + " chiffres.");
		logger.error("La longueur du nombre propos� ne correspond pas au param�tre d�fini dans config.properties");
	}
}
