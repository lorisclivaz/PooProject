/*
* Author : Vivian Bridy & Loris Clivaz
* Date creation : 30 avr. 2018
*/
package Images;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Classe responsable de l'affichage de l'image d'un contact
 * 
 * @author Vivian
 *
 */
public class ImageContact extends JButton
{
	
	/**
	 * Constructeur de la classe ImageContact
	 * 
	 * @author Vivian
	 */
	public ImageContact() {

		setPreferredSize(new Dimension(100, 100));
		
	}
	
	public void paintComponent(Graphics g){
		
		try {
	    	
	        Image img = ImageIO.read(new File("images/icones/contact.png"));
	        g.drawImage(img, 0, 0, this);
	        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	        
	      } catch (IOException e) {
	        e.printStackTrace();
	      }                
	    
	  }
}
