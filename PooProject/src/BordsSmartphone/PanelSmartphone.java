/*
 * Author : Vivian Bridy & Loris Clivaz
 * Date creation : 14 mai 2018
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
 * Classe qui va gérer les bords arrondi du smarphone
 * 
 * @author Loris
 *
 */

public class PanelSmartphone extends JPanel
{

	/**
	 * Constructeur de la classe PanelSmartphone
	 * 
	 * - définit le layout du panel
	 * 
	 * @author Loris
	 */

	public PanelSmartphone() 
	{		

		this.setLayout(new BorderLayout());


	}

	public void paintComponent(Graphics g)
	{

		try {

			Image img = ImageIO.read(new File("images/background/smartphone.png"));

			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

		} catch (IOException e) {
			e.printStackTrace();
		}                

	} 
}


