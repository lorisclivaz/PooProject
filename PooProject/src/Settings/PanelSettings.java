/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 16 mai 2018
* 
*/
/**
 * 
 */
package Settings;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import GalerieApp.GalleryPanel;
import MainFrame.Frame;
import Panels.PanelSettings2;

/**
 * @author Loris_Clivaz
 *
 */
public class PanelSettings extends JPanel
{
	PanelSettings2 panelsettings2 = new PanelSettings2("Settings", "Settings");
	JPanel center = new JPanel();

	JButton button = new JButton("Fond d'écran");
	
	
	private Frame frame;
	
	public PanelSettings(Frame frame)
	{
		this.frame = frame;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
	
		button.setPreferredSize(new Dimension(480, 80));
		button.addActionListener(new ClickButton());
		
		center.setLayout(new FlowLayout());
		center.add(button);
		
		this.add(panelsettings2, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
	}
	
	class ClickButton implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			
			frame.getCardLayout().show(frame.getTriPanel(), "gallerypanel");
			System.out.println("cliqué");
			
		}
		
	}
}


