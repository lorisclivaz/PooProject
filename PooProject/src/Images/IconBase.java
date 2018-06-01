/*
* 
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
 * Classe responsable de l'affichage des icones
 * 
 * @author Vivian
 *
 */
public class IconBase extends JButton
{

	private String url;
	private int width;
	private int heigth;
	private String urlImageBase = "images/photos/contact-1.png";
	
	/**
	 * Constructeur de la classe IconBase
	 * 
	 * @param url : l'url de l'icone
	 * @param width : la largeur de l'icone
	 * @param heigth : la hauteur de l'icone
	 * @author Vivian
	 */
	
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

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public void paintComponent(Graphics g){

		try {

			Image img = ImageIO.read(new File(url));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			
			Image img;
			try {
				img = ImageIO.read(new File(urlImageBase));
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}                

	}   
}
