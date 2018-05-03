/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 3 mai 2018
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
import javax.swing.JPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class ImagePhoto extends JPanel
{
	
	String url;
	
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
