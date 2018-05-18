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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Calculette.Calculette;
import ContactApp.ContactPanel;
import GalerieApp.GalleryPanel;
import Images.IconBase;
import Images.IconLock;
import Images.ImageFond;
import Panels.UpPanel;

/**
 * @author Loris_Clivazs
 *
 */
public class Frame extends JFrame
{
	//Ajout des panelss
	ContactPanel contactpanel = new ContactPanel();
	GalleryPanel gallerypanel = new GalleryPanel();
	VerrouPanel verroupanel = new VerrouPanel();
	Calculette calculette = new Calculette();
	
	JPanel backpanel = new JPanel();
	JPanel mainpanel = new JPanel();
	UpPanel uppanel = new UpPanel();
	ImageFond imagefond = new ImageFond();
	

	private boolean lockFrame = false;





	//Ajout des icons
	IconLock iconlock = new IconLock();
	

	IconBase iconcontact = new IconBase("images/icones/contact.png", 100,100);
	IconBase icongallery = new IconBase("images/icones/gallery.png", 100,100);
	IconBase iconpower = new IconBase("images/icones/power.png", 100,100);
	IconBase iconmusic = new IconBase("images/icones/music.png", 100,100);
	IconBase iconcalculette = new IconBase("images/icones/calculette.png", 100,100);
	IconBase iconhorloge = new IconBase("images/icones/horloge.png", 100,100);
	IconBase iconyoutube = new IconBase("images/icones/youtube.png", 100,100);
	IconBase iconfacebook = new IconBase("images/icones/facebook.png", 100,100);
	IconBase iconinstagram = new IconBase("images/icones/instagram.png", 100,100);
	IconBase iconagenda = new IconBase("images/icones/agenda.png", 100,100);
	IconBase iconphoto = new IconBase("images/icones/photo.png", 100,100);
	IconBase lockMain = new IconBase("images/icones/lockmain.png", 100,100);

	
	
	//tri des panels static pour avoir accès dans le panel d'accueil
	private     CardLayout cardLayout = new CardLayout();
	private    JPanel triPanel = new JPanel(cardLayout);
	
	
	
	
	public Frame() 
	{
		
		
		
		this.setSize(480, 860);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setBackground(new Color(0, 0, 0));
		this.setLayout(new BorderLayout());
		
		//ajout du panel du haut
		this.add(uppanel, BorderLayout.NORTH);
		

		//ajout du panel du bas
		this.add(backpanel, BorderLayout.SOUTH);
		
		//choisir la taille
		backpanel.setPreferredSize(new Dimension(480, 80));
		backpanel.setBackground(Color.BLACK);
		backpanel.setLayout(new FlowLayout(30,165,10));
		backpanel.add(iconlock);
		iconlock.addActionListener(new ClickLock());
		
		
		this.add(triPanel);
		triPanel.add(verroupanel, "verroupanel");
		triPanel.add(mainpanel, "mainpanel");
		
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setPreferredSize(new Dimension(480, 40));
		mainpanel.add(imagefond);
		
		imagefond.setLayout(new FlowLayout(30,45,70));
		imagefond.add(iconcontact);
		imagefond.add(icongallery);
		imagefond.add(iconpower);
		imagefond.add(iconmusic);
		imagefond.add(iconcalculette);
		imagefond.add(iconhorloge);
		imagefond.add(iconyoutube);
		imagefond.add(iconfacebook);
		imagefond.add(iconinstagram);		
		imagefond.add(iconagenda);
		imagefond.add(iconphoto);
		imagefond.add(lockMain);
		
		
		triPanel.add(gallerypanel, "gallerypanel");
		triPanel.add(contactpanel,"contactpanel");
		triPanel.add(calculette, "calculette");
		
		icongallery.addActionListener(new ClickGallery());
		iconcontact.addActionListener(new ClickContact());	
		iconpower.addActionListener(new ClickPower());
		iconcalculette.addActionListener(new ClickCalculette());
		lockMain.addActionListener(new ClickLockMain());
		}
	
	
	
	
	
	
	class ClickLockMain implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{

			cardLayout.show(triPanel, "verroupanel");
			
			lockFrame = false;
		}
		
	}



	class ClickCalculette implements ActionListener
	{

		
		@Override
		public void actionPerformed(ActionEvent e) {

			
			cardLayout.show(triPanel, "calculette");
		}
		
	}



	//quand on clique sur le bouton lock
	class ClickLock implements ActionListener
	{

		
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(lockFrame == true)
			{
				cardLayout.show(triPanel, "mainpanel");

			}
			System.out.println("clique bouton milieu");
			

		}
		
	}
	//Quand on clique sur l'icon power
	
		class ClickPower implements ActionListener{

			
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				System.exit(0);
			
			}
			
		}
		
		public class VerrouPanel extends JPanel
		{

			ImageFond imagefond = new ImageFond();
			ImageFond imagefond2 = new ImageFond();
			IconBase lock = new IconBase("images/icones/padlock.png", 50,50);

			JPanel boutonlock = new JPanel();

			
			// Date
			private JLabel date = new JLabel();
			final private DateFormat DATEFORMATDATE = new SimpleDateFormat("EEEE dd MMM");
			private Timer timerDate = new Timer(0, new CurrentDate());

			// Heure
			private JLabel heure = new JLabel();
			final private DateFormat DATEFORMATHEURE = new SimpleDateFormat("HH:mm");
			private Timer timer = new Timer(0, new CurrentTime());
			
			public VerrouPanel() 
			{

			
				this.setPreferredSize(new Dimension(480, 40));
				
				this.setLayout(new BorderLayout());


				imagefond.setLayout(new BorderLayout());
				
				boutonlock.setLayout(new BorderLayout());
				boutonlock.add(imagefond2);

				imagefond2.setLayout(new FlowLayout(100,215,20));
				imagefond2.add(lock);


				lock.addActionListener(new ClickLock());


				// Heure
				timer.start();
				imagefond.add(heure, BorderLayout.NORTH);
				heure.setHorizontalAlignment(JLabel.CENTER);
				heure.setForeground(Color.WHITE);
				heure.setFont(new Font("Montserrat", Font.BOLD, 70));

				// Date
				timerDate.start();
				imagefond.add(date, BorderLayout.CENTER);
				date.setHorizontalAlignment(JLabel.CENTER);
				date.setForeground(Color.WHITE);
				date.setFont(new Font("Montserrat", Font.BOLD, 30));
				

				this.add(imagefond, BorderLayout.CENTER);
				this.add(boutonlock, BorderLayout.SOUTH);
				
				
				
				
				
				
			}

			private class ClickLock implements ActionListener
			{

				
				@Override
				public void actionPerformed(ActionEvent e) {

					cardLayout.show(triPanel, "mainpanel");
					lockFrame = true;
					System.out.println("cliqué");
					
				}
				
			}
			
			class CurrentDate implements ActionListener
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Calendar now = Calendar.getInstance();
					date.setText(DATEFORMATDATE.format(now.getTime()));
				}
			}
			
			class CurrentTime implements ActionListener
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Calendar now = Calendar.getInstance();
					heure.setText(DATEFORMATHEURE.format(now.getTime()));
				}
			}
			
		}

		
		//Quand on clique sur l'icon contacts

		class ClickContact implements ActionListener{

			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("cliqué");
				cardLayout.show(triPanel, "contactpanel");
			}
			
		}
		
		//Quand on clique sur l'icon gallery
		
		class ClickGallery implements ActionListener{

			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{			
				
				cardLayout.show(triPanel, "gallerypanel");
				
			}
			
		}
		
		
		
		
		
	
		public void setImagefond(ImageFond imagefond) {
			this.imagefond = imagefond;
		}


		
		public CardLayout getCardLayout() {
			return cardLayout;
		}





		public void setCardLayout(CardLayout cardLayout) {
			this.cardLayout = cardLayout;
		}





		public JPanel getTriPanel() {
			return triPanel;
		}





		public void setTriPanel(JPanel triPanel) {
			this.triPanel = triPanel;
		}
		
}

