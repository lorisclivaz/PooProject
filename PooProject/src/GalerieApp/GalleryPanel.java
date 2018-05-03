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
import java.awt.Component;
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

import ContactApp.Contact;
import Images.ImagePhoto;
import MainFrame.MainPanel;








/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel 
{





	
	MenuH1PanelGallery menuh1panel = new MenuH1PanelGallery("Gallery", "Gallery");

	String path;
	Photo current;
	private ArrayList<Photo> photos = new ArrayList<Photo>();


	public GalleryPanel()
	{
		


		//scanGalleryFolder();

		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);




		//Ajout de la scrool bar
		JScrollBar bar = new JScrollBar();
		this.add(bar, BorderLayout.EAST);		

	
		
		nbrObjets();

	}

	public void nbrObjets()
	{
		
		
		File dossier = new File("serialisationPhoto");
		File[] f = dossier.listFiles();
		
		for (int i = 0; i < f.length; i++)
		{
			path = f[i].getAbsolutePath(); 
			current = deSerializeObject(path);
			
			
			
			ImagePhoto imagephoto = new ImagePhoto(current.getUrl());
			
			this.add(imagephoto, BorderLayout.CENTER);
		}
		
		
	}
	
	public Photo deSerializeObject(String path) { 
		try {
			FileInputStream fichier = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Photo cs = (Photo) ois.readObject();
			return cs;
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}


	
}