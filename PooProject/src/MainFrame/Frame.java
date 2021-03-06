/*
 * Author : Vivian Bridy & Loris Clivaz
 * Date creation : 14 mai 2018
 */

package MainFrame;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Batterie.Kernel32;
import BordsSmartphone.PanelSmartphone;
import Calculette.Calculette;
import ContactApp.ContactPanel;
import GalerieApp.GalleryPanel;
import Horloge.HorlogePanel;
import Images.IconBase;
import Images.IconLock;
import Images.ImageFond;
import Panels.PanelSettings;
import Panels.UpPanel;
import PasswordPanel.PasswordPanel;

/**
 * Classe qui va g�rer tous le smartphone
 * 
 * @author Loris
 *
 */

public class Frame extends JFrame
{

	ImageFond imagefond = new ImageFond();


	//Ajout des panelss
	ContactPanel contactpanel = new ContactPanel(this,false);
	VerrouPanel verroupanel = new VerrouPanel();
	SettingsPanel settingspanel = new SettingsPanel(imagefond, this);
	GalleryPanel gallerypanel = new GalleryPanel(imagefond, settingspanel, this, contactpanel);


	Calculette calculette = new Calculette();
	HorlogePanel horlogepanel = new HorlogePanel();
	JPanel backpanel = new JPanel();
	JPanel mainpanel = new JPanel();
	UpPanel uppanel = new UpPanel();


	//CameraPanel camera = new CameraPanel();
	PasswordPanel psw = new PasswordPanel(this);
	changePswdPanel changepswdpanel = new changePswdPanel(psw, this);
	PanelSmartphone smartphone = new PanelSmartphone();

	private boolean lockFrame = false;


	//Ajout des icons
	IconLock iconlock = new IconLock();
	IconBase iconcontact = new IconBase("images/icones/contact.png", 60,60);
	IconBase icongallery = new IconBase("images/icones/gallery.png", 60,60);
	IconBase iconpower = new IconBase("images/icones/power.png", 60,60);
	IconBase iconsettings = new IconBase("images/icones/settings.png",  60,60);
	IconBase iconcalculette = new IconBase("images/icones/calculette.png", 60,60);
	IconBase iconhorloge = new IconBase("images/icones/horloge.png",  60,60);
	IconBase lockMain = new IconBase("images/icones/lockmain.png",  60,60);
	IconBase vide ;


	//tri des panels static pour avoir acc�s dans le panel d'accueil
	private     CardLayout cardLayout = new CardLayout();
	private    JPanel triPanel = new JPanel(cardLayout);



	/**
	 * Constructeur de la classe Frame
	 * 
	 * -D�termine la size et le background de la frame
	 * -Definit les icones 
	 * -UpPanel et BackPanel
	 * -R�cup�ration de tous les panels cr��
	 * 
	 * @author loris
	 */

	public Frame() 
	{




		// Param�tres de la frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(480, 860);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true); // Ne pas afficher les boutons de la frame
		setBackground(new Color(0, 0, 0, 0)); // Fond transparent Rouge,Bleu,Vert,Opacite

		//ajout du panel du haut
		smartphone.add(uppanel, BorderLayout.NORTH);


		//ajout du panel du bas
		smartphone.add(backpanel, BorderLayout.SOUTH);

		//choisir la taille
		backpanel.setPreferredSize(new Dimension(480, 80));
		backpanel.setOpaque(false);
		backpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		backpanel.setLayout(new FlowLayout(30,150,10));
		backpanel.add(iconlock);
		iconlock.addActionListener(new ClickLock());

		//On ajout le cardLayout au smartphone pour changer de panel facilement
		smartphone.add(triPanel);
		triPanel.add(verroupanel, "verroupanel");
		triPanel.add(mainpanel, "mainpanel");

		mainpanel.setLayout(new BorderLayout());
		mainpanel.setPreferredSize(new Dimension(480, 40));

		mainpanel.add(imagefond, BorderLayout.CENTER);

		imagefond.setLayout(new FlowLayout(30,50,70));

		imagefond.add(icongallery);
		imagefond.add(iconsettings);
		imagefond.add(iconcalculette);


		//Ajout des icones vides pour la mise en page (un peu du bricolage)
		for (int i = 0; i < 13; i++) 
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


		triPanel.add(psw, "psw");
		triPanel.add(settingspanel, "settingspanel");
		triPanel.add(changepswdpanel, "changepswdpanel");

		//Ajout des listeners pour les diff�rentes icones du smartphone
		icongallery.addActionListener(new ClickGallery());
		iconcontact.addActionListener(new ClickContact());	
		iconpower.addActionListener(new ClickPower());
		iconcalculette.addActionListener(new ClickCalculette());
		lockMain.addActionListener(new ClickLockMain());
		iconhorloge.addActionListener(new ClickHorloge());
		iconsettings.addActionListener(new ClickSettings());

		//On ajout le smartphone � la frame
		this.add(smartphone);


	}


	/**
	 * Classe qui va g�rer le click sur l'icone r�glage
	 * 
	 * @author Loris
	 *
	 */
	private class ClickSettings implements ActionListener
	{


		@Override
		public void actionPerformed(ActionEvent e) 
		{


			cardLayout.show(triPanel, "settingspanel");
		}

	}



	/**
	 * Classe qui va g�rer le click sur l'icone horloge
	 * 
	 * @author Loris
	 *
	 */

	private class ClickHorloge implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{

			cardLayout.show(triPanel, "horlogepanel");

		}

	}


	/**
	 * Classe qui va g�rer le click sur l'icone du lock du main
	 * 
	 * @author Loris
	 *
	 */

	private class ClickLockMain implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{

			cardLayout.show(triPanel, "verroupanel");

			lockFrame = false;
		}

	}


	/**
	 * Classe qui va g�rer le click sur l'icone calculette
	 * 
	 * @author Loris
	 *
	 */

	private class ClickCalculette implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{

			cardLayout.show(triPanel, "calculette");
		}

	}



	/**
	 * Classe qui va g�rer le click sur l'icone du lock pour revenir � chaque fois en arri�re
	 * 
	 * @author Loris
	 *
	 */
	private class ClickLock implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(lockFrame == true)
			{
				cardLayout.show(triPanel, "mainpanel");

			}

		}

	}


	/**
	 * Classe qui va g�rer le click sur l'icone power
	 * 
	 * @author Loris
	 *
	 */

	private class ClickPower implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) 
		{

			try
			{
				boolean finished = false;
				while (!finished)
				{
					// Ex�cution de la t�che

					Thread.sleep (3000); // En pause pour deux secondes

					System.exit(0);
				}
			}
			catch (InterruptedException exception){}
		}



	}



	/**
	 * Classe qui va g�rer l'application r�glage
	 * 
	 * @author Loris
	 *
	 */
	public class SettingsPanel extends JPanel
	{

		PanelSettings panelsettings = new PanelSettings("R�glage", "R�glage");


		JButton button = new JButton("Changer le mot de passe");
		JButton button2 = new JButton("Changer le fond d'�cran");

		JPanel grid = new JPanel();
		JPanel flow = new JPanel();
		JPanel flow2 = new JPanel();

		ImageFond changeWallpaper;

		Frame frame;


		boolean isReglage = false;



		/**
		 * Constructeur de la classe SettingsPanel
		 * 
		 * -D�finit la taille du panel
		 * -Ajout du bouton pour changer le mot de passe
		 * -Ajout de deux panel pour le design 
		 * 
		 * @param changeWallpaper le fond d'�cran chang�
		 * @param frame la frame qui est utilis�e
		 * @author Loris
		 */

		public SettingsPanel(ImageFond changeWallpaper, Frame frame) 
		{

			this.frame = frame;
			this.changeWallpaper = changeWallpaper;
			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());

			flow.setLayout(new FlowLayout(40, 10, 10));
			flow.add(button);

			flow2.setLayout(new FlowLayout(40, 10, 10));
			flow2.add(button2);




			button.setPreferredSize(new Dimension(459, 55));
			button.setBackground(Color.WHITE);
			button.setFont(new Font("2.TimesRoman ",Font.BOLD,25));
			button.addMouseListener(new Movement());

			button2.setPreferredSize(new Dimension(459, 55));
			button2.setBackground(Color.WHITE);
			button2.setFont(new Font("2.TimesRoman ",Font.BOLD,25));
			button2.addMouseListener(new Movement2());



			button.addActionListener(new ClickChange());
			button2.addActionListener(new ClickFond());


			grid.setLayout(new GridLayout(4, 1));

			grid.add(flow);
			grid.add(flow2);

			this.add(panelsettings, BorderLayout.NORTH);
			this.add(grid, BorderLayout.CENTER);
		}

		/**
		 * Classe qui va nous emmener vers le panel galerie
		 * 
		 * @author Loris
		 *
		 */

		private class ClickFond implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{

				frame.getCardLayout().show(frame.getTriPanel(), "gallerypanel");

				isReglage = true;

			}

		}

		/**
		 * Classe qui va g�rer le mouvement de la souris sur le bouton changer
		 * 
		 * @author Loris
		 *
		 */

		private	class Movement2 implements MouseListener
		{



			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				button2.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				button2.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
			}

		}


		/**
		 * Classe qui va g�rer le mouvement de la souris sur le bouton changer
		 * 
		 * @author Loris
		 *
		 */

		private	class Movement implements MouseListener
		{



			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				button.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				button.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
			}

		}

		public boolean isReglage() {
			return isReglage;
		}
		public void setReglage(boolean isReglage) {
			this.isReglage = isReglage;
		}
	}


	/**
	 * Classe qui va g�rer le panel du changement du mot de passe
	 * 
	 * @author Loris
	 *
	 */

	private class changePswdPanel extends JPanel
	{

		PasswordPanel changePsw;
		Frame frame;

		PanelSettings change = new PanelSettings("Mot de passe", "Mot de passe");

		JPanel grid = new JPanel();
		JPasswordField ecrire = new JPasswordField();
		JPasswordField ecrire2 = new JPasswordField();

		JLabel label1 = new JLabel("Entrer l'ancien mot de passe :");
		JLabel label2 = new JLabel("Entrer le nouveau mot de passe : ");
		JLabel ancienIncorrect = new JLabel("Donn�es saisies incorrectes");
		String nouveau ;

		JButton button = new JButton("Change");



		/**
		 * Contructeur de la class ChangePswdPanel
		 * 
		 * @param changePsw : r�cup�ration des valeurs de PasswordPanel
		 * @param frame : r�cup�ration des valeurs de la frame
		 * 
		 * @author Loris
		 */

		public changePswdPanel(PasswordPanel changePsw, Frame frame) 
		{

			this.frame = frame;
			this.changePsw = changePsw;
			this.setPreferredSize(new Dimension(480, 40));
			this.setLayout(new BorderLayout());

			//Design du bouton
			button.setBackground(Color.WHITE);
			button.setFont(new Font("2.TimesRoman ",Font.BOLD,30));
			button.addMouseListener(new MouseMouvement());

			grid.setLayout(new FlowLayout(50, 70, 50));

			ecrire.setPreferredSize(new Dimension(360, 20));
			ecrire2.setPreferredSize(new Dimension(360, 20));
			ecrire2.addKeyListener(new PressedEnter(button));
			//Design des labels
			label1.setFont(new Font("2.TimesRoman ",Font.BOLD,20));
			label2.setFont(new Font("2.TimesRoman ",Font.BOLD,20));
			ancienIncorrect.setFont(new Font("2.TimesRoman ",Font.BOLD,20));

			//Ancien mot de passe
			grid.add(label1);
			grid.add(ecrire);


			//nouveau mot de passe
			grid.add(label2);
			grid.add(ecrire2);


			grid.add(button);

			this.add(change, BorderLayout.NORTH);
			this.add(grid, BorderLayout.CENTER);


			//Ajout de l'action listener sur le bouton changer

			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					//V�rification de la saisie
					if(ecrire.getText().equals(changePsw.lecturePswd()))
					{

						nouveau = ecrire2.getText();

						ecrire2.setText(null);
						ecrire.setText(null);

						frame.getCardLayout().show(frame.getTriPanel(), "mainpanel");

						changePsw.fichier(nouveau);
						changePsw.lecturePswd();

					}
					else
					{
						ecrire.setText(null);
						ecrire2.setText(null);

						grid.add(ancienIncorrect);
					}
				}
			});


		}

		/**
		 * KeyListener qui est activ� lorsque l'on touche ENTER
		 * 
		 * @author Vivian
		 *
		 */

		public class PressedEnter implements KeyListener
		{
			JButton enregistrement;
			public PressedEnter(JButton enregistrement)
			{
				this.enregistrement = enregistrement;
			}

			@Override
			public void keyPressed(KeyEvent e) 
			{

				if(e.getKeyCode() == 10)
				{
					enregistrement.doClick();
				}


			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		}


		/**
		 * Classe qui va g�rer le design du bouton avec le mouvement de la souris
		 * 
		 * @author Loris
		 *
		 */
		private class MouseMouvement implements MouseListener
		{

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				button.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
				button.setBackground(Color.WHITE);
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
			}

		}
	}

	/**
	 * Classe qui va g�rer le bouton change au moment du clique (on change de panel)
	 * 
	 * @author Loris
	 *
	 */
	private class ClickChange implements ActionListener
	{


		@Override
		public void actionPerformed(ActionEvent e) 
		{

			cardLayout.show(triPanel, "changepswdpanel");

		}

	}

	/**
	 * Classe VerrouPanel qui contient L'image du lock avec l'heure et la date
	 * 
	 * @author Loris
	 *
	 */

	private class VerrouPanel extends JPanel
	{

		//Batterie
		Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();


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

		JPanel grid1 = new JPanel();
		JPanel flow = new JPanel();
		JPanel flow2 = new JPanel();

		/**
		 * Constructeur de la classe VerrouPanel
		 * 
		 * -D�finit la size du verrouPanel
		 * -Ajout de l'heure et de la date 
		 * 
		 * @author Loris
		 */

		public VerrouPanel() 
		{

			Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
			IconBase iconBatterie = new IconBase(batteryStatus.getBatterystate(), 80,60);
			String batteriePourc = batteryStatus.getBatterystate2();

			JLabel label = new JLabel(batteriePourc);

			label.setForeground(Color.WHITE);
			label.setFont(new Font("Montserrat", Font.BOLD, 30));

			imagefond.setUrl("images/background/imagefond.jpg", true); 		//on force le background du verrou
			imagefond2.setUrl("images/background/imagefond.jpg", true);

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
			heure.setHorizontalAlignment(JLabel.CENTER);
			heure.setForeground(Color.WHITE);
			heure.setFont(new Font("Montserrat", Font.BOLD, 70));

			flow.setLayout(new FlowLayout(5, 195,130));
			flow.setOpaque(false);
			flow.add(iconBatterie);

			flow2.setLayout(new FlowLayout(5, 200, 0));
			flow2.add(label);
			flow2.setOpaque(false);

			grid1.setLayout(new GridLayout(3, 1));
			grid1.add(heure);
			grid1.add(flow);
			grid1.add(flow2);
			grid1.setOpaque(false);

			imagefond.add(grid1,BorderLayout.CENTER);
			// Date
			timerDate.start();
			imagefond.add(date, BorderLayout.NORTH);
			date.setHorizontalAlignment(JLabel.CENTER);
			date.setForeground(Color.WHITE);
			date.setFont(new Font("Montserrat", Font.BOLD, 30));



			this.add(imagefond, BorderLayout.CENTER);
			this.add(boutonlock, BorderLayout.SOUTH);






		}



		/**
		 * Classe qui va g�rer le moment du Click sur le lock
		 * 
		 * @author Loris
		 *
		 */

		private class ClickLock implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {

				cardLayout.show(triPanel, "psw");

			}

		}


		/**
		 * Classe qui va g�rer La date du moment 
		 * 
		 * @author Loris
		 *
		 */

		private class CurrentDate implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Calendar now = Calendar.getInstance();
				date.setText(DATEFORMATDATE.format(now.getTime()));
			}
		}


		/**
		 * Classe qui va g�rer L'heure du moment
		 * 
		 * @author Loris
		 *
		 */

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


	/**
	 * Classe qui va g�rer le moment du click sur l'icone contact
	 * 
	 * @author Loris
	 *
	 */

	private class ClickContact implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent arg0)
		{

			cardLayout.show(triPanel, "contactpanel");
		}

	}

	/**
	 * Classe qui va g�rer le moment du clique sur la galerie
	 * 
	 * @author Loris
	 *
	 */

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

