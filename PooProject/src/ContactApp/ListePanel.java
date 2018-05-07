/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import Panels.MenuH1PanelContact;

public class ListePanel extends JPanel
{
	private String titre = "Contacts";
	JScrollPane s = new JScrollPane();
	JPanel Panel = new JPanel();
	private MenuH1PanelContact menuh1panel = new MenuH1PanelContact(titre, getClass().getSimpleName());
	public   CardLayout cardLayout;
	public  JPanel triPanel;
	ModifContact modifcontact;
	
	public ListePanel(CardLayout cardlayout,JPanel triPanel)
	{
		this.cardLayout = cardlayout;
		this.triPanel = triPanel;
	
		
//		this.setLayout(new GridLayout(0, 1, 7, 7));
		// TODO Auto-generated constructor stube
		this.setSize(480,400);
		this.setLayout(new BorderLayout());
		this.add(menuh1panel, BorderLayout.NORTH);
		
		Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
		this.setBackground(Color.decode("#FFFFFF"));
		
		//On calcule le nombre de contact dans le dossier serialisation
		File dossier = new File("serialisation");
		File[] f = dossier.listFiles();
		String path;
		Contact current;
		int nombreFichier = 0;
		for (int i = 0 ; i < f.length ; i++) {
		  if (f[i].isFile()) {
			path = f[i].getAbsolutePath();
			current = this.deSerializeObject(path);
			JButton bouton = new JButton();
			bouton.setText(current.getNom()+" "+current.getPrenom());
			bouton.addActionListener(new ClickContact(current));
			bouton.setPreferredSize(new Dimension(470,100));
			Panel.add(bouton);
		  }
		}
		Panel.setPreferredSize(new Dimension(480,640));
		s.getViewport().add(Panel);
		add(s);
		
		JScrollBar bar = new JScrollBar();
		bar.setVisible(true);
		Panel.add(bar);
		
		setVisible(true);
		
		menuh1panel.setCardLayout(cardLayout,triPanel);
		
	}
	
	public Contact deSerializeObject(String path) { 
		try {
			FileInputStream fichier = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Contact cs = (Contact) ois.readObject();
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
	
	class ClickContact implements ActionListener{

		Contact contact;
		public ClickContact(Contact contact) {
			// TODO Auto-generated constructor stub
			this.contact = contact;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			ModifContact modifcontact = new ModifContact(contact, cardLayout,triPanel);
			
			triPanel.add(modifcontact, "modifcontact");
			
			cardLayout.show(triPanel, "modifcontact");
		}
		
	}

}
