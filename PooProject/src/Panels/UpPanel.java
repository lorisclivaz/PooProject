/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 30 avr. 2018
* 
*/
/**
 * 
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


/**
 * @author Loris_Clivaz
 *
 */
public class UpPanel extends JPanel
{
	
	public UpPanel() 
	{
		
		
		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.BLACK);
//		Graphics g;
//		this.paintComponent(g);
		//test pour bord arrondi
	
	
	}
	@Override
	protected void paintComponent(Graphics g) {
		   g.setColor(getBackground());
		   g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
		}
}
