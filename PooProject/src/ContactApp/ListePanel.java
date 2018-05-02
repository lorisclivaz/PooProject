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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
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
	
	public ListePanel(CardLayout cardlayout,JPanel triPanel)
	{
		this.cardLayout = cardlayout;
		this.triPanel = triPanel;
		
//		this.setLayout(new GridLayout(0, 1, 7, 7));
		// TODO Auto-generated constructor stube
		this.setSize(480,400);
		
		this.add(menuh1panel);
		
		Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
		this.setBackground(Color.decode("#FFFFFF"));
		
		
		Contact[] bookContact = new Contact[30];
		
		for(int i=0;i<30;i++) {
			bookContact[i] = new Contact("Bridy"+i, "Vivian"+i, "Route"+i, "Num"+i, "vivian"+i+"@gmail.com");
		}
		
		for(int i=0; i<30; i++) {
			JButton bouton = new JButton();
			bouton.setText(bookContact[i].getNom()+" "+bookContact[i].getPrenom());
			Panel.add(bouton);	
		}
		s.getViewport().add(Panel);
		add(s);
		setVisible(true);
		
		menuh1panel.setCardLayout(cardLayout,triPanel);
		
	}

}
