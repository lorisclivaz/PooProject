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

import javax.swing.JPanel;

/**
 * @author Loris_Clivaz
 *
 */
public class GalleryPanel extends JPanel
{

	
	public GalleryPanel()
	{

		this.setLayout(new BorderLayout());

		this.setPreferredSize(new Dimension(480, 40));
		this.setBackground(Color.RED);

	}
}
