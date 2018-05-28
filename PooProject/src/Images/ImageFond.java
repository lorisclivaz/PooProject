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
	String changeImage=lecture();

	/**
	 * Constructeur avec aucun param�tre de la classe ImageFond
	 * 
	 * @author Vivian
	 */
	public ImageFond()
	{
		
	}

	/**
	 * Constructeur avec un param�tre de la classe ImageFond
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
			
			e.printStackTrace();
		}                

	}


	/**
	 * @param changeImage : l'url de l'image chang�e
	 * @author Vivian
	 */
	public void setUrl(String changeImage) {

		ecriture(changeImage);
		this.changeImage = changeImage;
	}
	
	public void setUrl(String changeImage, boolean check) {
		this.changeImage = changeImage;
	}
	
	private void ecriture(String changeImage)
	{
		
		
		File dossier = new File("background");
		dossier.mkdir();
		
		File fichier = new File(dossier,"url.txt");
	
		
		try {
			fichier.createNewFile();
			
			
			FileWriter ecriture = new FileWriter(fichier);
			BufferedWriter bfwrite = new BufferedWriter(ecriture);
			bfwrite.write(changeImage); 
			bfwrite.close();
		
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	
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
		
		
		return resultat;
		
	}

	
	

}
