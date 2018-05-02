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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import MainFrame.MainPanel;







/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel 
{






	MenuH1PanelGallery menuh1panel = new MenuH1PanelGallery("Gallery", "Gallery");

	
	Photo photos [] ;


	public GalleryPanel()
	{
		this.photos  = new Photo[10];


		//scanGalleryFolder();

		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);




		//Ajout de la scrool bar
		JScrollBar bar = new JScrollBar();
		this.add(bar, BorderLayout.EAST);		




	}

	

	private static void deserializeObject(Photo	photo) throws IOException, ClassNotFoundException 
	{
		
		
		FileInputStream fichier = new FileInputStream("serialisationPhoto/photo.ser");	
		
		BufferedInputStream bfichier = new BufferedInputStream(fichier);
		
		ObjectInputStream lireobject = new ObjectInputStream(bfichier);	
		
		Photo valeur = (Photo) lireobject.readObject();
		
		
		
		
		lireobject.close();
		
	}

	
}