/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 8 juin 2018
* 
*/
/**
 * 
 */
package ContactApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Loris_Clivaz
 *
 */
public class Contact implements Serializable 
{
	private String nom,prenom,adresse,localite,telephone,mail,urlImage;
	int id=Integer.parseInt(lecture()); //Variable qui va stocker le numéro du contact en création
	
	public Contact(String nom,String prenom,String adresse,String localite,String mail,String telephone,String urlImage)
	{
		setId();
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
		setLocalite(localite);
		setTelephone(telephone);
		setMail(mail);
		setUrlImage(urlImage);
	}
	
	private String lecture()
	{
		String resultat="";
		File dossier = new File("src/ContactApp");
		dossier.mkdir();
		
		File fichier = new File(dossier,"id.txt");
		
		try {
		
			FileReader read = new FileReader(fichier);
			BufferedReader bfread = new BufferedReader(read);
			resultat = bfread.readLine();
			read.close();
			bfread.close();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		return resultat;
		
	}
	
	private void ecriture(int id)
	{
		this.id = id;
		
		File dossier = new File("src/ContactApp");
		dossier.mkdir();
		
		File fichier = new File(dossier,"id.txt");
	
		
		try {
			fichier.createNewFile();
			
			
			FileWriter ecriture = new FileWriter(fichier);
			BufferedWriter bfwrite = new BufferedWriter(ecriture);
			bfwrite.write(Integer.toString(id)); 
			bfwrite.close();
		
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId()
	{
		id++;
		this.ecriture(id);
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public String getUrlImage()
	{
		return urlImage;
	}

	public void setUrlImage(String urlImage)
	{
		this.urlImage = urlImage;
	}

	public String getPrenom()
	{
		return prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}
	
	public String getLocalite()
	{
		return localite;
	}

	public void setLocalite(String localite)
	{
		this.localite = localite;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}
}
