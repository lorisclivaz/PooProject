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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class IconGallery extends JButton
{

	public IconGallery() {

		setPreferredSize(new Dimension(100, 100));
	}
	
public void paintComponent(Graphics g){
		
		try {
	    	
	        Image img = ImageIO.read(new File("images/icones/gallery.png"));
	        g.drawImage(img, 0, 0, this);
	        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	        
	      } catch (IOException e) {
	        e.printStackTrace();
	      }                
	    
	  }   
}
