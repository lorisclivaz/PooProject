/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListePanel extends JPanel
{
	
//	private JScrollPane scrollPane = new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//	private JScrollPane scrollPane = new JScrollPane();
	
	public ListePanel()
	{
		// TODO Auto-generated constructor stube
		this.setBackground(Color.decode("#FFFFFF"));
		
//		this.setLayout(new BorderLayout());
		
		Contact[] bookContact = new Contact[10];
		
		for(int i=0;i<10;i++) {
			bookContact[i] = new Contact("Bridy"+i, "Vivian"+i, "Route"+i, "Num"+i, "vivian"+i+"@gmail.com");
		}
		
		for(int i=0; i<10; i++) {
			JButton bouton = new JButton();
			bouton.setText(bookContact[i].getNom()+" "+bookContact[i].getPrenom());
			bouton.setPreferredSize(new Dimension(480,78));
			this.add(bouton);	
		}
		
	}

}
