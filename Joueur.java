import java.util.Scanner;

public class Joueur {
	
	static long proposition;
	static int longueurProposition;
	
	public Joueur() {
		
	}

	public static void proposerNombre() {
		
		System.out.println("Proposition : ");
		do {	
			Scanner sc = new Scanner (System.in);
			
			//Si l'entrée clavier n'est pas un Long
			if (!sc.hasNextLong()) {
				Menu.erreurNombre();
	      sc.next(); 
	      continue;
		}	  
			proposition = sc.nextLong();	
			longueurProposition = String.valueOf(proposition).length();//longueurProposition est égal à la longueur (nombre de chiffres) dans proposition
			
			if (longueurProposition != Jeu.longueurNombreMystere)
				Menu.erreurNombre();
		} while (longueurProposition != Jeu.longueurNombreMystere);//Boucle tant que la proposition n'a pas le même nombre de chiffres que le paramètre longueurNombreMystere
		
		System.out.println("Votre proposition est : "+proposition);
	}
}
