import java.util.Scanner;

public class Menu {
	
	public Menu() {
		
		byte choix = 0;
		System.out.println("Bonjour !");
		
		do {
			
		System.out.println("\nÀ quoi souhaitez-vous jouer ?");
		System.out.println("\n1· Plus ou moins \n2· Mastermind \n3· Quitter");
			
		Scanner sc = new Scanner (System.in);
		
		if (!sc.hasNextByte()) {//Si l'entrée clavier n'est pas un byte
			System.out.println("\nVeuillez choisir parmi les propositions.");
      sc.next(); 
      continue;
  }
		choix = sc.nextByte();
		switch (choix){
		
		case 1: PlusOuMoins plusOuMoins = new PlusOuMoins();//Plus ou Moins
		choix = 0;
		break;
		
		case 2: Mastermind mastermind = new Mastermind();//Mastermind
		choix = 0;
		break;
		
		case 3: System.out.println("À bientôt !");//Quitter
		break;
		
		default : System.out.println("\nVeuillez choisir parmi les propositions.");
		
	 }
	} while (choix != 1 && choix != 2 && choix != 3);
 }
}
