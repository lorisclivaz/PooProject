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
public class IconBase extends JButton
{

	private String url;
	private int width;
	private int heigth;
	
	public IconBase(String url, int width, int heigth) {

		setPreferredSize(new Dimension(width,heigth));

		setBorderPainted(false); 
		setContentAreaFilled(false); 
		setFocusPainted(false); 
		setOpaque(false);

		this.url = url;
		this.width = width;
		this.heigth = heigth;
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
