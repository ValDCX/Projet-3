import java.util.Scanner;
import org.apache.log4j.Logger;

public class Menu {
	
	private static Logger logger = Logger.getLogger(Main.class);

	public Menu() {
		logger.info("Affichage du menu");
		byte choix = 0;
		System.out.println("Bonjour !");

		do {

			System.out.println("\nÀ quoi souhaitez-vous jouer ?");
			System.out.println("\n1· Plus ou moins \n2· Mastermind \n3· Quitter");

			Scanner sc = new Scanner(System.in);

			// Si l'entrée clavier n'est pas un byte
			if (!sc.hasNextByte()) {
				Erreur.erreurChoix();
				sc.next();
				continue;
			}
			choix = sc.nextByte();
			switch (choix) {

			case 1:
				PlusOuMoins plusOuMoins = new PlusOuMoins();// Plus ou Moins
				choix = 0;
				break;

			case 2:
				Mastermind mastermind = new Mastermind();// Mastermind
				choix = 0;
				break;

			case 3:
				System.out.println("À bientôt !");// Quitter
				logger.info("Fermeture du jeu");
				break;

			default:
				Erreur.erreurChoix();
			}
		} while (choix != 1 && choix != 2 && choix != 3);
	}
}
