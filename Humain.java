import java.util.Scanner;

public class Humain extends Joueur{

	public Humain() {
		
	}
	
	public static void proposerNombre() {
		Jeu.compteur++;
		System.out.print("Proposition : ");
		do {	
			Scanner sc = new Scanner (System.in);
		 
			proposition = sc.nextLine();	
			
			longueurProposition = String.valueOf(proposition).length();//longueurProposition est �gal � la longueur (nombre de chiffres) dans proposition
			
			if (!proposition.matches ("[0-9]+") || longueurProposition != Jeu.longueurNombreMystere)
				Erreur.erreurNombre();
			
		} while (longueurProposition != Jeu.longueurNombreMystere || !proposition.matches("[0-9]+"));//Boucle tant que la proposition n'a pas le m�me nombre de chiffres que le param�tre longueurNombreMystere
	}
}
