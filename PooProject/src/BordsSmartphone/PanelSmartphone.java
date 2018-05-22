/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 22 mai 2018
* 
*/
/**
 * 
 */
package BordsSmartphone;

import java.awt.BorderLayout;
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
public class PanelSmartphone extends JPanel
{

	

	
	public PanelSmartphone() 
	{

//		setSize(480, 860);
		
		
		this.setLayout(new BorderLayout());
		
		

	
	}
	
public void paintComponent(Graphics g){
		
		try {
	    	
	        Image img = ImageIO.read(new File("images/background/smartphone.png"));
	        
	        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	        
	      } catch (IOException e) {
	        e.printStackTrace();
	      }                
	    
	  } 
}
	

