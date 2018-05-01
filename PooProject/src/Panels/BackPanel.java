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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Images.IconBase;
import Images.IconLock;
import MainFrame.Frame;

/**
 * @author Loris_Clivaz
 *
 */
public class BackPanel extends JPanel
{

	IconLock iconlock = new IconLock();
	
	public BackPanel() 
	{

	this.setPreferredSize(new Dimension(480, 80));
	this.setBackground(Color.BLACK);
	
	this.setLayout(new FlowLayout(50,210,10));
	this.add(iconlock);
	
	iconlock.addActionListener(new ClickLock());
	}
	
	class ClickLock implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent e) {

			Frame.cardLayout.show(Frame.triPanel, "mainpanel");

		}
		
	}
}
