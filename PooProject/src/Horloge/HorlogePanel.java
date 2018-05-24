/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris & Vivian Bridy
 * Date creation: 18 mai 2018
 * 
 */
/**
 * 
 */
package Horloge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Panels.PanelHorloge;

/**
 *Classe responsable de l'application horloge
 *
 *@author Vivian
 */
public class HorlogePanel extends JPanel
{

	PanelHorloge panelhorloge = new PanelHorloge ("Horloge", "Horloge");
	
	JPanel center = new JPanel ();

	//Heures
		private JLabel heure = new JLabel();
		final private DateFormat DATEFORMAT = new SimpleDateFormat("     HH:mm:ss");
		private Timer timer = new Timer(0, new CurrentTime());

	/**
	 * Constructeur de la classe HorlogePanel
	 * 
	 * @author Vivian
	 */
	public HorlogePanel() 
	{

		//Choix du layout et de la dimension du panel
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		center.setBackground(Color.DARK_GRAY);
		
		timer.start();
		heure.setForeground(Color.WHITE);
		heure.setFont(new Font("Arial", Font.BOLD, 70));
		
		
		center.add(heure);
		
		this.add(panelhorloge, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);

		
	}
	
	/**
	 * ActionListener qui affiche l'heure actuelle dans le panel
	 * 
	 * @author Vivian
	 *
	 */
	
	class CurrentTime implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			
            heure.setText(DATEFORMAT.format(now.getTime()));
            
            
		}
	}
}
