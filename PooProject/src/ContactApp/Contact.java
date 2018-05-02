/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.io.Serializable;

public class Contact implements Serializable
{
	private String nom,prenom,adresse,telephone,mail;
	
	public Contact(String nom,String prenom,String adresse,String telephone,String mail) {
		// TODO Auto-generated constructor stub
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
		setTelephone(telephone);
		setMail(mail);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
