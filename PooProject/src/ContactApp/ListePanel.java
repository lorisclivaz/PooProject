/*
* Author : Vivian Bridy
* Date creation : 30 avr. 2018
*/
package ContactApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ListePanel extends JPanel{
	
	
	public ListePanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(480, 78));
		this.setBackground(Color.decode("#FFFFFF"));
//		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		for(int i=0; i<10; i++) {
			JButton bouton = new JButton();
			bouton.setText("Bouton"+i);
			bouton.setPreferredSize(new Dimension(480,78));
			this.add(bouton);
		}
		
		
		
	}
}
