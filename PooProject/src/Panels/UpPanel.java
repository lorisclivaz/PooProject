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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import MainFrame.MyBoder;


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
		this.setOpaque(false);
		

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
	
	public class MyBoder implements Border{ 

	    public Insets getBorderInsets(Component c) { 
	        return new Insets(0, 0, 0, 0);//ou autre chose cela dépend de si tu veux rendre parametrable 
	    } 

	    public boolean isBorderOpaque() { 
	        return false;//ou autre chose cela dépend de si tu veux rendre parametrable 
	    } 

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) { 
	        g.setColor(Color.BLUE);//ou une autre couleur que tu peux rendre paramétrable 
	        int arc = 25;//tu peux aussi le rendre configurable 
	        int adjustXY = 1;//pour ajuster le dessin en x et y 
	        int adjustWH = 2;//idem pour width et height 
	        //pour eviter les escalier sur l'arrondi 
	        Graphics2D g2 = (Graphics2D)g; 
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
	        g2.drawRoundRect(x+adjustXY, y+adjustXY, width-adjustWH, height-adjustWH, arc, arc); 
	    } 
	}
}

