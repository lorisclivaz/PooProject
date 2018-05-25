/*

* Author: Clivaz Loris & Vivian Bridy
* Date creation: 30 avr. 2018
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
 * Classe responsable de l'affichage de l'icone lock
 * 
 * @author Vivian
 *
 */
public class IconLock extends JButton
{

	/**
	 * Constructeur de la classe IconLock
	 * 
	 * @author Vivian
	 */
	public IconLock( ) {

		setPreferredSize(new Dimension(150, 50));

		setBorderPainted(false); 
		setContentAreaFilled(false); 
		setFocusPainted(false); 
		setOpaque(false);

		
	}

	public void paintComponent(Graphics g){

		try {

			Image img = ImageIO.read(new File("images/icones/lock.jpg"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}                

	}   
}
