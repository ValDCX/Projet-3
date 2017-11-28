import java.util.Random;
import java.util.Scanner;

public class Jeu {

	String nomDuJeu;
	String resultat;
	static int longueurNombreMystere = 4;
	static int nombreUtilises[] = {0,1,2,3,4,5,6,7,8,9};
	long nombreMystere;
	
	public Jeu(String nomDuJeu) {
		
		this.nomDuJeu = nomDuJeu;
		byte choix = 0;
		
		System.out.println(afficherNom());
		do {
			System.out.println("\nChoisissez un mode de jeu :");
			System.out.println("\n1· Challenger \n2· Défenseur \n3· Duel \n4· Quitter");
				
			Scanner sc = new Scanner (System.in);
			
			//Si l'entrée clavier n'est pas un byte
			if (!sc.hasNextByte()) {
				Menu.erreurChoix();
	      sc.next(); 
	      continue;
		}
			choix = sc.nextByte();
			switch (choix){
			
			case 1: this.challenger();
			break;
			
			case 2: this.defenseur();
			break;
			
			case 3: this.duel();
			break;
			
			case 4: break;
		
			default : Menu.erreurChoix();
			}
		} while (choix != 1 && choix != 2 && choix != 3	&& choix !=4);
	}
	
	//******Méthodes*******
	
	public void challenger() {
		System.out.println("********MODE CHALLENGER********");
		genererNombreMystere();
		Humain.proposerNombre();
	}
	
	public void defenseur() {
		System.out.println("********MODE DEFENSEUR********");
		genererNombreMystere();
	}
	
	public void duel() {
		System.out.println("********MODE DUEL********");
		genererNombreMystere();
	}
	
	//Génération du nombre mystère
	public void genererNombreMystere() {
		
		int maximumPossible;
		
		maximumPossible = nombreUtilises.length-1;//On récupère le nombre maximum à être utilisé dans nombreUtilises
		
		Random random = new Random();
		
		int chiffreNombreMystere[] = new int [longueurNombreMystere];
		String sChiffre = "";
		
		for (int i = 0; i < longueurNombreMystere; i++) {//On génère un chiffre aléatoire jusqu'à atteindre la longueur définie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
			sChiffre += chiffreNombreMystere[i];//On ajoute le chiffre généré au String sChiffre
		}
		
		//Si le premier chiffre aléatoire est un zéro, il est remplacé par un autre chiffre aléatoire entre 1 et maximumPossible (sinon il ne s'affichera pas)
		if (sChiffre.charAt(0) == '0') {
			int r = random.nextInt(maximumPossible-1)+1;
			sChiffre = r+sChiffre.substring(1, sChiffre.length());
		}
		
		nombreMystere = Long.parseLong((sChiffre));//On convertit sChiffre en Long dans nombreMystere
		System.out.println(nombreMystere);
	}
	
	//Retourner le nom du jeu sélectionné
	public String afficherNom() {
		return this.nomDuJeu;
	}
}
