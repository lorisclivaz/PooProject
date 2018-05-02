/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListePanel extends JPanel
{
	
	JScrollPane s = new JScrollPane();
	JPanel Panel = new JPanel();
	
	public ListePanel()
	{
		
		
//		this.setLayout(new GridLayout(0, 1, 7, 7));
		// TODO Auto-generated constructor stube
		this.setSize(480,400);
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
		
	}

}
