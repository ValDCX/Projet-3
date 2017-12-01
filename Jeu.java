import java.util.Random;
import java.util.Scanner;

public abstract class Jeu {
	
	Joueur joueur1;
	Joueur joueur2;
	String nomDuJeu;
	String resultat;
	static int longueurNombreMystere = 4;
	static int nombreUtilises[] = {0,1,2,3,4,5};
	static int compteur;
	String nombreMystere = "";
	
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
				Erreur.erreurChoix();
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
		
			default : Erreur.erreurChoix();
			}
		} while (choix != 1 && choix != 2 && choix != 3	&& choix !=4);
	}
	
	//******Méthodes*******
	
	public void challenger() {
		System.out.println("********MODE CHALLENGER********");
		compteur = 0;
		genererNombreMystere();
		joueur1 = new Humain();
		//On boucle tant que le nombre mystère n'est pas trouvé
		do {
			joueur1.proposerNombre();
			comparerNombres(joueur1);
		} while (!String.valueOf(joueur1.proposition).equals(nombreMystere));
		System.out.println("Bravo ! Vous avez trouvé le nombre mystère en "+compteur+" coups !");
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
		
		for (int i = 0; i < longueurNombreMystere; i++) {//On génère un chiffre aléatoire jusqu'à atteindre la longueur définie dans longueurNombreMystere
			chiffreNombreMystere[i] = random.nextInt(maximumPossible+1);
			
			nombreMystere += chiffreNombreMystere[i];
		}
		
		System.out.println(nombreMystere);
	}
	
	//Retourner le nom du jeu sélectionné
	public String afficherNom() {
		return this.nomDuJeu;
	}
	
	//Comparer la proposition du joueur au nombre mystère
	public void comparerNombres(Joueur joueur) {
		
		String sProposition = String.valueOf(joueur.proposition);//On met la valeur de proposition dans un String
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
		System.out.println("Résultat : "+resultat+"\n");
	}
}
