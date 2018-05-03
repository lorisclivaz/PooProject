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

import Images.ImagePhoto;
import MainFrame.MainPanel;








/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel 
{





	
	MenuH1PanelGallery menuh1panel = new MenuH1PanelGallery("Gallery", "Gallery");

	
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
			System.out.println(f[i].getAbsolutePath()); 
		}
		
		
	}
	
	private void deserial()
	{
		try 
		{
			FileInputStream fichier = new FileInputStream("serialisationPhoto/photos.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			photos = (ArrayList<Photo>) ois.readObject();
			ois.close();
		} 
		catch (IOException e) 
		{
			photos = new ArrayList<Photo>();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();

		}
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