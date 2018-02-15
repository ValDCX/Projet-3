import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;


public abstract class Jeu {

	private static Logger logger = Logger.getLogger(Main.class);
	
	Joueur joueur1;
	Joueur joueur2;
	String nomDuJeu;
	static String resultat;
	static int longueurNombreMystere;
	static int nombreUtilises[];
	static int compteur;
	static int coupsMax;
	static String nombreMystere = "";
	Properties prop = new Properties();
	InputStream input = null;

	public Jeu(String nomDuJeu) {

		try {

			input = new FileInputStream("config.properties");

			// Chargement du fichier config
			prop.load(input);
			
			longueurNombreMystere = Integer.valueOf(prop.getProperty("longueurPlusOuMoins"));
			coupsMax = Integer.valueOf(prop.getProperty("coupsMax"));
			nombreUtilises = new int[Integer.valueOf(prop.getProperty("couleurs"))];
			for (int i = 0; i < Integer.valueOf(prop.getProperty("couleurs")); i++) {
				nombreUtilises[i] = i;
			}

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

		this.nomDuJeu = nomDuJeu;
		byte choix = 0;

		System.out.println(afficherNom());
		do {
			System.out.println("\nChoisissez un mode de jeu :");
			System.out.println("\n1· Challenger \n2· Défenseur \n3· Duel \n4· Quitter");

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
				this.challenger();
				break;

			case 2:
				this.defenseur();
				break;

			case 3:
				this.duel();
				break;

			case 4:
				break;

			default:
				Erreur.erreurChoix();
			}
		} while (choix != 1 && choix != 2 && choix != 3 && choix != 4);
	}

	// ******Méthodes*******

	public void challenger() {

	}

	public void defenseur() {

	}

	public void duel() {

	}

	// Génération du nombre mystère
	public void genererNombreMystere() {
		nombreMystere = "";
		Random random = new Random();

		int chiffreNombreMystere[] = new int[longueurNombreMystere];

		for (int i = 0; i < longueurNombreMystere; i++) {// On génère un chiffre aléatoire jusqu'à atteindre la longueur
																											// définie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(9 + 1);

			nombreMystere += chiffreNombreMystere[i];
		}
		logger.info("Nombre mystère généré : "+nombreMystere);
	}

	// Retourner le nom du jeu sélectionné
	public String afficherNom() {
		return this.nomDuJeu;
	}

	// Comparer la proposition du joueur au nombre mystère
	public void comparerNombres(Joueur joueur) {

		String sProposition = String.valueOf(Joueur.proposition);// On met la valeur de proposition dans un String
		resultat = "";

		for (int i = 0; i < longueurNombreMystere; i++) {
			int chiffrePropose = Character.getNumericValue(sProposition.charAt(i));
			int chiffreNombreMystere = Character.getNumericValue(nombreMystere.charAt(i));

			if (chiffrePropose > chiffreNombreMystere)
				resultat += "-";
			else if (chiffrePropose < chiffreNombreMystere)
				resultat += "+";
			else if (chiffrePropose == chiffreNombreMystere)
				resultat += "=";
		}
		System.out.println("Résultat : " + resultat + "\n\n--------\n");
		logger.info("Nombres comparés, résultat : "+resultat);
	}

	public void finPartie(String vainqueur) {
		logger.info("Partie terminée");
		if (compteur >= coupsMax && !Joueur.proposition.equals(nombreMystere))
			System.out.println(
					"Vous avez atteint la limite de coups (" + coupsMax + ") ! Le nombre mystère était : " + nombreMystere + ".");
		else
			System.out.println("Bravo ! " + vainqueur + " trouvé le nombre mystère en " + (compteur - 1) + " coups !");
	}

	public void initCompteur() {
		logger.info("Compteur de coups initialisé");
		compteur = 1;
	}

	public void afficherCompteur() {
		logger.info("Affichage du compteur de coups (coup n°"+compteur+")");
		System.out.println("Coup n°" + (compteur));
	}
	
	public void devMode() {
		if (Main.modDev == 1)
		{
			System.out.println("[Mode développeur] Le nombre mystère est : "+nombreMystere);
		}
	}

}
