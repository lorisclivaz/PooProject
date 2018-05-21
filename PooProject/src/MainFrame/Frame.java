package MainFrame;
/*
 * Frame
 * Author: Clivaz Loris
 * Date creation: 30 avr. 2018
 * 
 */
/**
 * 
 */


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
import Camera.CameraPanel;
import ContactApp.ContactPanel;
import GalerieApp.GalleryPanel;
import Horloge.HorlogePanel;
import Images.IconBase;
import Images.IconLock;
import Images.ImageFond;
import Panels.UpPanel;
import PasswordPanel.PasswordPanel;

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
	HorlogePanel horlogepanel = new HorlogePanel();
	JPanel backpanel = new JPanel();
	JPanel mainpanel = new JPanel();
	UpPanel uppanel = new UpPanel();
	ImageFond imagefond = new ImageFond();
	CameraPanel camera = new CameraPanel();
	PasswordPanel psw = new PasswordPanel(this);
	
	private boolean lockFrame = false;





	





	//Ajout des icons
	IconLock iconlock = new IconLock();


	IconBase iconcontact = new IconBase("images/icones/contact.png", 60,60);
	IconBase icongallery = new IconBase("images/icones/gallery.png", 60,60);
	IconBase iconpower = new IconBase("images/icones/power.png", 60,60);
	IconBase iconmusic = new IconBase("images/icones/music.png",  60,60);
	IconBase iconcalculette = new IconBase("images/icones/calculette.png", 60,60);
	IconBase iconhorloge = new IconBase("images/icones/horloge.png",  60,60);
	IconBase iconyoutube = new IconBase("images/icones/youtube.png",  60,60);
	IconBase iconfacebook = new IconBase("images/icones/facebook.png",  60,60);
	IconBase iconinstagram = new IconBase("images/icones/instagram.png",  60,60);
	IconBase iconagenda = new IconBase("images/icones/agenda.png", 60,60);
	IconBase iconphoto = new IconBase("images/icones/photo.png",  60,60);
	IconBase lockMain = new IconBase("images/icones/lockmain.png",  60,60);
	IconBase vide ;



	//tri des panels static pour avoir acc�s dans le panel d'accueil
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
		mainpanel.add(imagefond, BorderLayout.CENTER);

		imagefond.setLayout(new FlowLayout(30,50,70));

		imagefond.add(icongallery);

		imagefond.add(iconmusic);
		imagefond.add(iconcalculette);
		imagefond.add(iconyoutube);
		imagefond.add(iconfacebook);
		imagefond.add(iconinstagram);		
		imagefond.add(iconagenda);
		imagefond.add(iconphoto);


		for (int i = 0; i < 8; i++) 
		{
			imagefond.add(new IconBase("images/icones/vide.png",  60,60));
		}

		imagefond.add(iconpower);
		imagefond.add(iconhorloge);
		imagefond.add(iconcontact);
		imagefond.add(lockMain );




		triPanel.add(gallerypanel, "gallerypanel");
		triPanel.add(contactpanel,"contactpanel");
		triPanel.add(calculette, "calculette");
		triPanel.add(horlogepanel, "horlogepanel");
		triPanel.add(camera,"camera");
		triPanel.add(psw, "psw");

		icongallery.addActionListener(new ClickGallery());
		iconcontact.addActionListener(new ClickContact());	
		iconpower.addActionListener(new ClickPower());
		iconcalculette.addActionListener(new ClickCalculette());
		lockMain.addActionListener(new ClickLockMain());
		iconhorloge.addActionListener(new ClickHorloge());
		iconphoto.addActionListener(new ClickPhoto());
		
		

	}
	



	private class ClickPhoto implements ActionListener
	{


		@Override
		public void actionPerformed(ActionEvent e)
		{

			System.out.println("clique camera");

			cardLayout.show(triPanel, "camera");
		}

	}
	private class ClickHorloge implements ActionListener
	{


		@Override
		public void actionPerformed(ActionEvent e) 
		{

			cardLayout.show(triPanel, "horlogepanel");



		}

	}





	private class ClickLockMain implements ActionListener
	{


		@Override
		public void actionPerformed(ActionEvent arg0) 
		{

			cardLayout.show(triPanel, "verroupanel");

			lockFrame = false;
		}

	}



	private class ClickCalculette implements ActionListener
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
		IconBase lock = new IconBase("images/icones/fingerprint.png", 70,70);

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

			imagefond2.setLayout(new FlowLayout(100,200,20));
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

				
				cardLayout.show(triPanel, "psw");
				
				


			}

		}

		private class CurrentDate implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Calendar now = Calendar.getInstance();
				date.setText(DATEFORMATDATE.format(now.getTime()));
			}
		}

		private class CurrentTime implements ActionListener
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

	private class ClickContact implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			System.out.println("cliqu�");
			cardLayout.show(triPanel, "contactpanel");
		}

	}

	//Quand on clique sur l'icon gallery

	private class ClickGallery implements ActionListener{


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
	
	public void setLockFrame(boolean lockFrame) {
		this.lockFrame = lockFrame;
	}

}

