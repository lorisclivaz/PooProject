/*
* Frame
* Author: Clivaz Loris
* Date creation: 30 avr. 2018
* 
*/
/**
 * 
 */
package MainFrame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import Panels.BackPanel;
import Panels.MainPanel;
import Panels.UpPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class Frame extends JFrame
{

	UpPanel uppanel = new UpPanel();
	BackPanel backpanel = new BackPanel();
	MainPanel mainpanel = new MainPanel();
	
	public Frame() 
	{

		this.setSize(480, 860);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new BorderLayout());
		
		this.add(uppanel, BorderLayout.NORTH);

		this.add(backpanel, BorderLayout.SOUTH);
		
		this.add(mainpanel, BorderLayout.CENTER); //Ecran de base qui changera au fur et à mesure
		
		
	}
	
}
