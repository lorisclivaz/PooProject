/*
 * Author: Clivaz Loris & Vivian Bridy
 * Date creation: 30 avr. 2018
 * 
 */
package Images;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
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
	String changeImage="images/background/imagefond.jpg";

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

		this.changeImage = changeImage;
	}    

	
	

}
