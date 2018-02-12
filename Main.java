import org.apache.log4j.Logger;

public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		logger.info("Jeu lancé");
		
		// On lance le menu pour choisir le jeu
		Menu menu = new Menu();
	}
}
