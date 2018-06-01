/*
 * Author: Clivaz Loris & Vivian Bridy
 * Date creation: 30 avr. 2018
 * 
 */
package Images;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Classe responsable de l'affichage du background du smartphone
 * 
 * @author Vivian
 *
 */
public class ImageFond extends JPanel
{
	String changeImage="imagesgallery/"+lecture();
	String backgroundBase = "images/background/imagefond.jpg";

	/**
	 * Constructeur avec aucun paramètre de la classe ImageFond
	 * 
	 * @author Vivian
	 */
	public ImageFond()
	{
		
	}

	/**
	 * Constructeur avec un paramètre de la classe ImageFond
	 * 
	 * @param changeImage : l'url de l'image de fond
	 * @author Vivian
	 */
	public ImageFond(String changeImage)
	{
		this.changeImage=changeImage;
	}

	public void paintComponent(Graphics g)
	{		
		try
		{

			Image img = ImageIO.read(new File(changeImage));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			
		} 
		catch (IOException e) 
		{
			Image img;
			try {
				img = ImageIO.read(new File(backgroundBase));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			System.out.println("imageBackground de base !");
		}                

	}


	/**
	 * @param changeImage : l'url de l'image changée
	 * @author Vivian
	 */
	public void setUrl(String changeImage) {

		ecriture(changeImage);
		this.changeImage = changeImage;
	}
	
	public void setUrl(String changeImage, boolean check) {
		this.changeImage = changeImage;
	}
	
	/**
	 * Méthode qui va écrire l'url de l'image du background dans un fichier
	 * 
	 * @param changeImage : l'url de l'image qu va être écrite
	 * @author Vivian
	 */
	
	private void ecriture(String changeImage)
	{
		String[] decoupe = changeImage.split("imagesgallery");
		
		File dossier = new File("background");
		dossier.mkdir();
		
		File fichier = new File(dossier,"url.txt");
		
		try {
			fichier.createNewFile();
			
			
			FileWriter ecriture = new FileWriter(fichier);
			BufferedWriter bfwrite = new BufferedWriter(ecriture);
			bfwrite.write(decoupe[1]); 
			bfwrite.close();
		
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Méthode qui va lire le contenu du fichier url.txt et retourner son contenu
	 * 
	 * @return : l'url de l'image écrite dans le fichier
	 * @author Vivian
	 */
	
	public String lecture()
	{
		String resultat="";
		File dossier = new File("background");
		dossier.mkdir();
		
		File fichier = new File(dossier,"url.txt");
		
		try {
		
			FileReader read = new FileReader(fichier);
			BufferedReader bfread = new BufferedReader(read);
			resultat = bfread.readLine();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		resultat = resultat.substring(1,resultat.length());
		
		
		return resultat;
		
	}

	
	

}
