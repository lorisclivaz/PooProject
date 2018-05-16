/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris
 * Date creation: 30 avr. 2018
 * 
 */
/**
 * 
 */
package Images;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GalerieApp.GalleryPanel;
import GalerieApp.GalleryPanel.PhotoPanel;
import GalerieApp.GalleryPanel.PhotoPanel.ClickFond;

/**
 * @author Loris_Clivaz
 *
 */
public class ImageFond extends JPanel
{
	String url="images/background/imagefond.png";


	public ImageFond() {



	}

	public ImageFond(String url) {

		this.url=url;

	}



	public void paintComponent(Graphics g){

		try {


			Image img = ImageIO.read(new File(url));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}                

	}    

	
	

}
