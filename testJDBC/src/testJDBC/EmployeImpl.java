package testJDBC;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeImpl implements IEmploye{
	private Bdd bdd=new Bdd();
	private ResultSet resultSet;
	private int status;
	@Override
	public int add(Employe employe) {
		String sql= "INSERT INTO Employe VALUES (NULL,?,?,?,?,?,?)";
		
		try {
			bdd.initPrepar(sql);
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMM-ddhhmmss");//210902 251763
			Date now=new Date();
			String matricule=formater.format(now);
			bdd.getPrepStatement().setString(1,"SA-"+matricule);
			bdd.getPrepStatement().setString(2,employe.getNom());
			bdd.getPrepStatement().setString(3,employe.getTelephone());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			bdd.getPrepStatement().setString(4,sdf.format(employe.getNaissance()));
			bdd.getPrepStatement().setInt(5,employe.getSalaire());
			bdd.getPrepStatement().setInt(6,employe.getId_service());
			status=bdd.execteMaj();
			bdd.closeConnection();
			System.out.println("L'employe "+employe.getNom()+" a bien été ajouté");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}
	
	public int delete(String matricule) {
		String sql= "DELETE FROM `Employe` WHERE matricule= ?";
		
		try {
			bdd.initPrepar(sql);
			bdd.getPrepStatement().setString(1,matricule);
			status=bdd.execteMaj();
			bdd.closeConnection();
			System.out.println("L'employe a bien été supprimé");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Employe> liste(int service) {
		List<Employe> employes=new ArrayList<Employe>();
		String sql="SELECT * FROM Employe WHERE id_service = ?";
		try {
			bdd.initPrepar(sql);
			bdd.getPrepStatement().setInt(1,service);
			resultSet=bdd.executeSelect();
			if(resultSet.next())
				System.out.println("      Matricule                     Nom            Téléphone         Naissance          Salaire");
			else
				System.out.println("Il n'y a pas d'employé dans ce service");
			while(resultSet.next()) {
				Employe employe=new Employe();
				System.out.println(resultSet.getString("matricule")+"           "+resultSet.getString("nom")+"        "+resultSet.getString("telephone")+"         "+resultSet.getString("naissance")+"         "+resultSet.getInt("salaire"));
				employe.setMatricule(resultSet.getString("matricule"));
				employe.setNom(resultSet.getString("nom"));
				employe.setTelephone(resultSet.getString("telephone"));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				employe.setNaissance(sdf.parse(resultSet.getString("naissance")));
				
				employe.setSalaire(resultSet.getInt("salaire"));
				employe.setId_service(resultSet.getInt("id_service"));
				employes.add(employe);
			}
			bdd.closeConnection();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return employes;
	}

	@Override
	public int updateUnString(String element,String matricule,String valeur) {
		String sql= "UPDATE Employe set "+element+" = ? WHERE matricule= ?";
		
		try {
			bdd.initPrepar(sql);
			bdd.getPrepStatement().setString(1,valeur);
			bdd.getPrepStatement().setString(2,matricule);
			
			status=bdd.execteMaj();
			bdd.closeConnection();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}

	@Override
	public int updateUnInt(String element,String matricule,int valeur) {
		String sql= "UPDATE Employe set "+element+" = ? WHERE matricule= ?";
		
		try {
			bdd.initPrepar(sql);
			bdd.getPrepStatement().setInt(1,valeur);
			bdd.getPrepStatement().setString(2,matricule);
			
			status=bdd.execteMaj();
			bdd.closeConnection();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}
	
	@Override
	public int update(Employe employe) {
		// TODO Stub de la méthode généré automatiquement
		return 0;
	}
	

}
