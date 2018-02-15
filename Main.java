import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	public static boolean argument;
	public static int modDev;

	public static void main(String[] args) {
		logger.info("Jeu lanc�");

		Properties prop = new Properties();
		InputStream input = null;
		OutputStream output = null;

		try {

			input = new FileInputStream("config.properties");

			// Chargement du fichier config
			prop.load(input);
			logger.info("Chargement du fichier config");
		} catch (IOException ex) {
			try {
				logger.error("Fichier config non trouv�");
				// En cas d'erreur, on cr��e un nouveau fichier config
				output = new FileOutputStream("config.properties");

				// Valeurs par d�faut
				prop.setProperty("longueurMastermind", "5");
				prop.setProperty("longueurPlusOuMoins", "5");
				prop.setProperty("couleurs", "10");
				prop.setProperty("coupsMax", "15");
				prop.setProperty("modeDeveloppeur", "0");
				logger.info("Fichier config par d�faut cr��");
				// Config sauvegard�e � la racine du projet
				prop.store(output, null);

			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (input != null) {
							try {
								input.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

		if (args.length > 0)
			modDev = Integer.parseInt(args[0]);
		else
			modDev = Integer.valueOf(prop.getProperty("modeDeveloppeur"));

		// On lance le menu pour choisir le jeu
		Menu menu = new Menu();
	}
}