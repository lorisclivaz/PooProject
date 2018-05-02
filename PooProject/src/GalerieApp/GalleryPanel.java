/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris
 * Date creation: 30 avr. 2018
 * 
 */
/**
 * 
 */
package GalerieApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Images.IconBase;
import Panels.MenuH1Panel;


/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel 
{



	private AjoutPhoto ajoutphoto = new AjoutPhoto();

	MenuH1Panel menuh1panel = new MenuH1Panel("Gallery", "Gallery");


	public GalleryPanel()
	{
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);



		this.add(ajoutphoto, BorderLayout.CENTER);

		JScrollPane scroll = new JScrollPane(ajoutphoto);

		this.add(scroll);




	}

	class ClickAjout implements ActionListener
	{


		@Override
		public void actionPerformed(ActionEvent arg0) {

			System.out.println("ta daronne");
		}

	}

}
