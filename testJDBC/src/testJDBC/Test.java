package testJDBC;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class Test {
	public static void main(String[] args) {
		Scanner lire=new Scanner(System.in);
		char reponse;
		do {
			traitement(lire);
			System.out.println("Voulez-vous continuer (O/N)?");
			reponse=lire.next().toUpperCase().charAt(0);
		    reponse=reesayer(reponse);
	    }while(reponse=='O');
	    
	    lire.close();
	}
	public static void traitement(Scanner lire) {
		int m=0;
		String mStr;
		do {
			menu();
			mStr=lire.next();
			if(isNum(mStr))
				m=Integer.parseInt(mStr);
			else
				System.out.println("Veuillez faire un choix valide !");
		}while(m<1 || m>4 || !isNum(mStr));
		
		switch (m) {
			case 1: ajouterEmploye(); break;
			case 2: modifierEmploye(); break;
			case 3: supEmploye(); break;
			case 4: afficherEmploye(); break;
		}
	}
	public static boolean isNum(String strNum) {
	    boolean ret = true;
	    try {

	        Double.parseDouble(strNum);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    return ret;
	}
	public static void ajouterEmploye() {
		try {
			Scanner lire=new Scanner(System.in);
		    EmployeImpl repo=new EmployeImpl();
		    Employe employe=new Employe();
		    System.out.print("Entrez le nom et le premon de l'employe : ");
		    employe.setNom(lire.next()+" "+lire.next());
		    System.out.print("Entrez le téléphone de l'employe : ");
		    employe.setTelephone(lire.next());
		    System.out.print("Entrez la date de naissance de l'employe : ");
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    
			employe.setNaissance(sdf.parse(lire.next()));
			System.out.print("Entrez le salaire de l'employe : ");
			employe.setSalaire(lire.nextInt());
			System.out.println("Veuillez choisir son service : ");
			listeService();
			employe.setId_service(lire.nextInt());
			repo.add(employe);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    
	}
	public static void modifierEmploye() {
		char reponse;
		Scanner lire=new Scanner(System.in);
	    EmployeImpl repo=new EmployeImpl();
	    System.out.print("Veuillez saisir son matricule : ");
	    String matricule=lire.next();
	    
	    System.out.println("Voulez-vous modifier son nom (O/N)?");
	    reponse=lire.next().toUpperCase().charAt(0);
	    reponse=reesayer(reponse);
	    if(reponse=='O') {
	    	System.out.println("Veuillez saisir le nouveau nom : ");
	    	String nom=lire.next();
	    	repo.updateUnString("nom", matricule, nom);
	    }
	    
	    System.out.println("Voulez-vous modifier son téléphone (O/N)?");
	    reponse=lire.next().toUpperCase().charAt(0);
	    reponse=reesayer(reponse);
	    if(reponse=='O') {
	    	System.out.println("Veuillez saisir le nouveau numéro de téléphone : ");
	    	String tel=lire.next();
	    	repo.updateUnString("telephone", matricule, tel);
	    }
	    
	    System.out.println("Voulez-vous modifier sa date de naissance (O/N)?");
	    reponse=lire.next().toUpperCase().charAt(0);
	    reponse=reesayer(reponse);
	    if(reponse=='O') {
	    	System.out.println("Veuillez saisir la nouvelle date de naissance : ");
	    	String naiss=lire.next();
	    	repo.updateUnString("naissance", matricule, naiss);
	    }
	    
	    System.out.println("Voulez-vous modifier son salaire (O/N)?");
	    reponse=lire.next().toUpperCase().charAt(0);
	    reponse=reesayer(reponse);
	    if(reponse=='O') {
	    	System.out.println("Veuillez saisir le nouveau salaire: ");
	    	int salaire=lire.nextInt();
	    	repo.updateUnInt("salaire", matricule, salaire);
	    }
	    
	    System.out.println("Voulez-vous modifier son service (O/N)?");
	    reponse=lire.next().toUpperCase().charAt(0);
	    reponse=reesayer(reponse);
	    if(reponse=='O') {
	    	listeService();
	    	System.out.println("Veuillez choisir le nouveau service : ");
	    	int service=lire.nextInt();
	    	repo.updateUnInt("id_service", matricule, service);
	    }
	}
	public static void supEmploye() {
		Scanner lire=new Scanner(System.in);
	    EmployeImpl repo=new EmployeImpl();
	    System.out.println("Veuillez entrer le matricule de l'employe à supprimer : ");
	    String matricule=lire.next();
	    System.out.println("Confirmer (O/N)? ");
	    char reponse=lire.next().toUpperCase().charAt(0);
	    reponse=reesayer(reponse);
	    if(reponse=='O')
	    	repo.delete(matricule);
	}
	public static void afficherEmploye() {
		Scanner lire=new Scanner(System.in);
	    EmployeImpl repo=new EmployeImpl();
	    System.out.println("Veuillez choisir le service à lister : ");
	    listeService();
	    int service=lire.nextInt();
	    repo.liste(service);
	}
	public static void menu() {
		System.out.print("-------------------- MENU ------------------------"
				+ "\n1---------------Ajouter Employe-------------------"
				+ "\n2---------------Modifier Employe------------------"
				+ "\n3---------------Suprimer Employe------------------"
				+ "\n4---------------Afficher les employes-------------"
				+ "\nEntrez votre choix : ");
	}
	public static void listeService() {

		Bdd bdd=new Bdd();
		ResultSet resultSet;
		String sql="SELECT libelle FROM service";
		bdd.initPrepar(sql);
		resultSet=bdd.executeSelect();
		try {
			int i=0;
			while(resultSet.next()) {
				i++;
				System.out.println(i+" - "+resultSet.getString("libelle"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static char reesayer(char reponse) {
		Scanner lire=new Scanner(System.in);
		while(reponse!='O' && reponse!='N' ) {
			System.out.print("Reessayer ? (O/N) : ");
			reponse=lire.next().toUpperCase().charAt(0);
		}
		return reponse;
		
	}
}

