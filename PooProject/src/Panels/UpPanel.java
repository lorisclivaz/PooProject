/*
 * Exercise W2Q3 - 2
 * Author: Clivaz Loris
 * Date creation: 2 mai 2018
 * 
 */
/**
 * 
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


/**
 * @author Loris_Clivaz
 *
 */
public class UpPanel extends JPanel{

	//Création de l'heure avec le nom swisscom
	Date madate = new Date();
	JLabel date = new JLabel();
	DateFormat formatHeure = new SimpleDateFormat("HH:mm");
	JLabel fourni = new JLabel("    Swisscom");


	// Heure
	private JLabel heure = new JLabel();
	final private DateFormat DATEFORMATHEURE = new SimpleDateFormat("HH:mm");
	private Timer timer = new Timer(0, new CurrentTime());

	//Constructeur qui définit la taille du panel ou il y aura la batterie l'heures ect...

	public UpPanel() 
	{
		this.setLayout(new BorderLayout(2,1));
		this.setPreferredSize(new Dimension(480, 50));
		this.setBackground(Color.BLACK);

		//heure
		Calendar now = Calendar.getInstance();



		// Heure
		timer.start();
		heure.setHorizontalAlignment(JLabel.CENTER);
		heure.setFont(new Font("Arial", Font.BOLD, 15));
		
		heure.setForeground(Color.WHITE);
		
		fourni.setForeground(Color.WHITE);
		fourni.setFont(new Font("Arial", Font.BOLD, 15));
		this.add(fourni, BorderLayout.WEST);
		this.add(heure, BorderLayout.EAST);
	}

	class CurrentTime implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Calendar now = Calendar.getInstance();
			heure.setText("      4G     "+DATEFORMATHEURE.format(now.getTime())+"    ");		}
	}
}

