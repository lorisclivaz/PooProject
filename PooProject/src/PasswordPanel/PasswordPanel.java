/*
* Exercise W2Q3 - 2
* Author: Clivaz Loris
* Date creation: 21 mai 2018
* 
*/
/**
 * 
 */
package PasswordPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MainFrame.Frame;


/**
 * @author Loris_Clivaz
 *
 */
public class PasswordPanel extends JPanel
{

	String resultat = "tdp";
	
	JLabel label = new JLabel("Password :");
	JTextField ecrire = new JTextField();
	JPanel texteLabel = new JPanel();
	JButton button = new JButton("Enter");
	JLabel faux = new JLabel("Wrong password, please try again");

	
	Frame frame;
	
	public PasswordPanel(Frame frame)
	{
		this.frame = frame;
		
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		
		label.setFont(new Font("Arial", Font.BOLD, 70));
		ecrire.setPreferredSize(new Dimension(360, 20));
		texteLabel.setLayout(new FlowLayout(50, 70, 50));
		
		button.setBackground(Color.GRAY);
		button.addActionListener(new ClickEnter());
		
		faux.setVisible(false);
		
		texteLabel.add(label);
		texteLabel.add(ecrire);
		texteLabel.add(button);
		texteLabel.add(faux);
		
		
		this.add(texteLabel, BorderLayout.CENTER);
		
		
	}
	
	class ClickEnter implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent e)
		{

			
			
			
			
			if(getReponse().equals(resultat))
			{
				frame.getCardLayout().show(frame.getTriPanel(), "mainpanel");
				faux.setVisible(false);
				frame.setLockFrame(true);
			}else
			{
				faux.setVisible(true);
			}
			
			ecrire.setText(null);
			
		}
		
	}
	
	
	public String getReponse()
	{
		
		String reponse = ecrire.getText();
		
		System.out.println(reponse);
		
		return reponse;
	}
}
