import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Properties prop = new Properties();
		InputStream input = null;
		OutputStream output = null;

		try {

			input = new FileInputStream("config.properties");

			// Chargement du fichier config
			prop.load(input);

			// Affichage de la config pour debug
			System.out.println(prop.getProperty("longueurMastermind"));
			System.out.println(prop.getProperty("longueurPlusOuMoins"));
			System.out.println(prop.getProperty("couleurs"));
			System.out.println(prop.getProperty("coupsMax"));

		} catch (IOException ex) {
			try {
				// En cas d'erreur, on créée un nouveau fichier config
				output = new FileOutputStream("config.properties");

				// set the properties value
				prop.setProperty("longueurMastermind", "5");
				prop.setProperty("longueurPlusOuMoins", "5");
				prop.setProperty("couleurs", "10");
				prop.setProperty("coupsMax", "15");

				// Config sauvegardée à la racine du projet
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
		// On lance le menu pour choisir le jeu
		Menu menu = new Menu();
	}
}
