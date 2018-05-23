/*
* Author: Clivaz Loris & Vivian Bridy
* Date creation: 3 mai 2018
* 
*/
package Images;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Classe responsable de l'affichage d'une photo dans la gallerie
 * 
 * @author Vivian
 *
 */
public class ImagePhoto extends JPanel
{
	
	String url;
	
	/**
	 * Constructeur de la classe ImagePhoto
	 * 
	 * @param url : l'url de l'image affichée
	 * @author Vivian
	 */
	public ImagePhoto(String url) {

		setPreferredSize(new Dimension(100, 100));
		
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
