import java.util.*;
import java.io.*;
import org.apache.log4j.Logger;

public class Menu {
	
	private static Logger logger = Logger.getLogger(Main.class);
	Properties prop = new Properties();
	InputStream input = null;

	public Menu() {
		logger.info("Affichage du menu");
		byte choix = 0;
		System.out.println("Bonjour !");
		
		try {
			
		input = new FileInputStream("config.properties");

		// Chargement du fichier config
		prop.load(input);
		
		Jeu.devMode = Integer.valueOf(prop.getProperty("modeDeveloppeur"));


	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

		do {

			System.out.println("\nÀ quoi souhaitez-vous jouer ?");
			System.out.println("\n1· Plus ou moins \n2· Mastermind \n3· Mode développeur \n4· Quitter");

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
				if (Jeu.devMode == 1) {
					Jeu.devMode = 0;
					System.out.println("Mode développeur désactivé.");
					logger.info("Mode développeur désactivé");
					choix = 0;
				}
				else {
					Jeu.devMode = 1;
					System.out.println("Mode développeur activé. La combinaison mystère sera affichée à chaque début de tour.");
					logger.info("Mode développeur activé");
					choix = 0;
				}
				break;
				
			case 4:
				System.out.println("À bientôt !");// Quitter
				logger.info("Fermeture du jeu");
				break;

			default:
				Erreur.erreurChoix();
			}
		} while (choix != 1 && choix != 2 && choix != 3 && choix != 4);
	}
}
