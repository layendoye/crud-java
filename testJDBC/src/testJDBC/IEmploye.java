package testJDBC;

import java.util.List;

public interface IEmploye {
	public int add(Employe employe);
	public List<Employe> liste(int service);
	public int update(Employe employe);
	public int updateUnString(String element,String matricule,String valeur);
	public int updateUnInt(String element,String matricule,int valeur);
}
