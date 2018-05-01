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

/**
 * @author Loris_Clivaz
 *
 */
public class IconLock extends JButton
{

	
	public IconLock( ) {

		setPreferredSize(new Dimension(50,50));

		setBorderPainted(false); 
		setContentAreaFilled(false); 
		setFocusPainted(false); 
		setOpaque(false);

		
	}

	public void paintComponent(Graphics g){

		try {

			Image img = ImageIO.read(new File("images/icones/lock.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}                

	}   
}
