/*
* Author: Clivaz Loris & Vivian Bridy
* Date creation: 21 mai 2018
* 
*/
package PasswordPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ContactApp.ContactPanel.MouseMovement;
import MainFrame.Frame;


/**
 * Classe responsable du PassWord
 * 
 * @author Vivian
 *
 */
public class PasswordPanel extends JPanel
{

	String resultat ;

	
	
	JLabel label = new JLabel("Entrer le mot de passe:");
	JTextField ecrire = new JTextField();
	JPanel texteLabel = new JPanel();
	JButton button = new JButton("Enter");
	JLabel faux = new JLabel("Mot de passe incorrect");

	
	Frame frame;
	
	/**
	 * Le constructeur de la classe PasswordPanel
	 * 
	 * @param frame : la frame qui est prot�g�e par mot de passe
	 * @author Vivian
	 */
	
	public PasswordPanel(Frame frame) 
	{
		

		
		lecturePswd();
		
		this.frame = frame;
		
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());
		
		
		
		label.setFont(new Font("Arial", Font.BOLD, 35));
		ecrire.setPreferredSize(new Dimension(360, 20));
		ecrire.addKeyListener(new PressedEnter());
		
		texteLabel.setLayout(new FlowLayout(50, 70, 50));
		
		
		button.setBackground(Color.WHITE);
		button.setFont(new Font("2.TimesRoman ",Font.BOLD,20));
		button.addActionListener(new ClickEnter());
		button.addMouseListener(new Button());
		
		faux.setVisible(false);
		faux.setFont(new Font("2.TimesRoman ",Font.BOLD,20));
		
		texteLabel.add(label);
		texteLabel.add(ecrire);
		texteLabel.add(button);
		texteLabel.add(faux);
		
		
		this.add(texteLabel, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * KeyListener qui est activ� lorsqu'on appuie sur la touche ENTER
	 * 
	 * @author Vivian
	 *
	 */
	
	public class PressedEnter implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == 10)
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
	 * M�thode qui va lire le contenu du fichier loris.txt
	 * 
	 * @return : le mot de passe
	 * @author Vivian
	 */
	public String lecturePswd()
	{
		File dossier = new File("password");
		dossier.mkdir();
		
		File fichier = new File(dossier,"mdp.txt");
		
		try {
		
			FileReader read = new FileReader(fichier);
			BufferedReader bfread = new BufferedReader(read);
			resultat = bfread.readLine();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		resultat = decrypt(resultat);
		
		return resultat;
		
	}
	
	/**
	 * M�thode qui va encrypt� le mot de passe
	 * 
	 * @param reponse : mdp entr� par l'utilisateur
	 * @author Vivian
	 */
	
	public void fichier(String reponse)
	{
		
		resultat = lecturePswd();
		
		File dossier = new File("password");
		dossier.mkdir();
		
		File fichier = new File(dossier,"loris.txt");
	
		reponse = encrypt(reponse);
		
		try {
			fichier.createNewFile();
			
			
			FileWriter ecriture = new FileWriter(fichier);
			BufferedWriter bfwrite = new BufferedWriter(ecriture);
			bfwrite.write(reponse); 
			bfwrite.close();
		
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * MouseListener sur le bouton enregistrer le mot de passe
	 * 
	 * @author Vivian
	 *
	 */
	
	class Button implements MouseListener
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
	
	/**
	 * ActionListener qui va s'activer lors du click sur le bouton d'entr�e
	 * 
	 * @author Vivian
	 *
	 */
	
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
				
		return reponse;
	}
	
	public void setResultat(String resultat) 
	{
		this.resultat = resultat;
	}
	
	public JTextField getEcrire()
	{
		return ecrire;
	}
	
	/**
	 * M�thode d'encryptage du mot de passe
	 * 
	 * @param password : le mot de passe qui va �tre crypt�
	 * @return : le mot de passe crypt�
	 * @author Vivian
	 */
	
	public String encrypt(String password)
	 {
	        String crypte= "";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48;  
	            crypte=crypte+(char)c; 
	        }
	        return crypte;
    }
	
	/**
	 * M�thode de d�cryptage du mot de passe
	 * 
	 * @param password : le mot de passe qui va �tre d�crypt�
	 * @return : le mot de passe decrypt�
	 * @author Vivian
	 */
	public String decrypt(String password)
	 {
	        String aCrypter= "";
	        for (int i=0; i<password.length();i++)  {
	            int c=password.charAt(i)^48;  
	            aCrypter=aCrypter+(char)c; 
	        }
	        return aCrypter;
    }

}
