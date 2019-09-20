package testJDBC;

import java.util.Date;

public class Employe {
	public int id;
	public String matricule;
	public String nom;
	public String telephone;
	public Date naissance;
	public int salaire;
	public int id_service;
	public Employe() {}
	public Employe(String matricule,String nom,String telephone,Date naissance,int salaire,int id_service) {
		this.matricule=matricule;
		this.nom=nom;
		this.telephone=telephone;
		this.naissance=naissance;
		this.salaire=salaire;
		this.id_service=id_service;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getNaissance() {
		return naissance;
	}
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
	public int getSalaire() {
		return salaire;
	}
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	public int getId_service() {
		return id_service;
	}
	public void setId_service(int id_service) {
		this.id_service = id_service;
	}
	

}
