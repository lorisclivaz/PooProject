/*
 * Author : Vivian Bridy & Loris Clivaz
 * Date creation : 14 mai 2018
 */

package Calculette;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Images.IconBase;
import Panels.PanelSettings;


/**
 * Classe qui va gérer la calculette
 * 
 * Reprise sur le site OpenClassroom et remanié à notre sauce
 * 
 * @author Loris_Clivaz
 *
 *@lienInternet : https://openclassrooms.com/courses/apprenez-a-programmer-en-java/tp-une-calculatrice
 */

public class Calculette extends JPanel
{

	// Panel du haut
	PanelSettings panelcalculette = new PanelSettings("Calculatrice", "Calculatrice");
	IconBase point = new IconBase("images/icones/points.png", 50,50);


	//Panel du centre
	JPanel center = new JPanel();
	JPanel bas = new JPanel();

	//Création du panel sur le click
	JPanel panelCop = new JPanel();

	//Tableau qui stocke les éléments de la calculatrice
	String [] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=",
			"C", "+", "-", "*", "/"};

	//bouton
	JButton [] tab_button = new JButton[tab_string.length];

	//JLabel ecran ou les chiffres seront notés
	private JLabel ecran = new JLabel();

	//variable qui va permettre de faire les calcules
	private double chiffre1;

	private String operateur;




	//choisir la dimension des boutons
	private Dimension dim = new Dimension(60, 60);


	private Dimension dim2 = new Dimension(90, 90);

	private boolean clicOperateur = false, update = false;

	/**
	 * Constructeur de la classe Calculette
	 * 
	 * - définit la taille du Panel et son layout
	 * - ajoute et affiche les différents panels
	 * -ajoute les coposants de la calculette
	 * 
	 * @author Loris
	 */

	public Calculette()
	{

		//choisir la dimension du panel de base
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());

		//on rajoute le panel par defaut des apps dans le north du panel de base
		this.add(panelcalculette, BorderLayout.NORTH);



		//on met le layout du centre pour l'ordre des panels
		center.setLayout(new BorderLayout());

		//panel bas 
		bas.setLayout(new FlowLayout());
		bas.setBackground(Color.GRAY);
		bas.setSize(new Dimension(480, 40));
		bas.add(point);
		//mets tous les composants dans le panel center
		Composant();



		this.add(center, BorderLayout.CENTER);
		this.add(bas, BorderLayout.SOUTH);




	}

	/**
	 * Permet de créer les boutons de la calculette :
	 * 
	 * - Font pour les boutons
	 * - Taille des boutons
	 * -Ajout des actionlistener sur les boutons
	 * -Ajout des composants sur le panel
	 * 
	 * @author Loris
	 */

	private void Composant() 
	{
		//police des boutons
		Font police2 = new Font("Arial", Font.BOLD, 50);

		//On définit la police d'écriture à utiliser
		Font police = new Font("Arial", Font.BOLD, 40);
		ecran = new JLabel("0");
		ecran.setFont(police);
		//On aligne les informations à droite dans le JLabel
		ecran.setHorizontalAlignment(JLabel.CENTER);
		ecran.setPreferredSize(new Dimension(220, 40));

		JPanel operateur = new JPanel();      
		operateur.setPreferredSize(new Dimension(200, 225));

		JPanel chiffre = new JPanel();
		chiffre.setLayout(new GridLayout(4, 3,5,5));
		chiffre.setPreferredSize(new Dimension(200, 225));

		JPanel panEcran = new JPanel();
		panEcran.setPreferredSize(new Dimension(220, 60));

		//On parcourt le tableau 
		//afin de créer nos boutons avec la couleur de fond changé
		//ainsi que la police de caractère

		for(int i = 0; i < tab_string.length; i++){
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dim);
			tab_button[i].setBackground(Color.darkGray);
			tab_button[i].setFont(police2);
			tab_button[i].setForeground(Color.WHITE);
			switch(i){

			//Pour chaque élément situé à la fin du tableau
			//et qui n'est pas un chiffre
			//on définit le comportement à avoir grâce à un listener
			case 11 :
				tab_button[i].addActionListener(new EgalListener());
				chiffre.add(tab_button[i]);
				tab_button[i].setBackground(Color.DARK_GRAY);
				break;
			case 12 :
				tab_button[i].setForeground(Color.red);
				tab_button[i].addActionListener(new ResetListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				tab_button[i].setBackground(Color.GRAY);
				break;
			case 13 :
				tab_button[i].addActionListener(new PlusListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				tab_button[i].setBackground(Color.GRAY);
				break;
			case 14 :
				tab_button[i].addActionListener(new MoinsListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				tab_button[i].setBackground(Color.GRAY);
				break;	
			case 15 :	
				tab_button[i].addActionListener(new MultiListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				tab_button[i].setBackground(Color.GRAY);
				break;
			case 16 :
				tab_button[i].addActionListener(new DivListener());
				tab_button[i].setPreferredSize(dim2);
				operateur.add(tab_button[i]);
				tab_button[i].setBackground(Color.GRAY);
				break;
			default :
				//Par défaut, ce sont les premiers éléments du tableau
				//donc des chiffres, on affecte alors le bon listener
				chiffre.add(tab_button[i]);
				tab_button[i].addActionListener(new ChiffreListener());
				break;
			}
		}
		panEcran.add(ecran);
		panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
		center.add(panEcran, BorderLayout.NORTH);
		center.add(chiffre, BorderLayout.CENTER);
		center.add(operateur, BorderLayout.SOUTH);
	}

	/**
	 * Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné :
	 * 
	 * -Différentes conditions selon l'opérateur 
	 * 
	 * @author Loris
	 */

	public void calcul(){
		if(operateur.equals("+")){
			chiffre1 = chiffre1 + 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if(operateur.equals("-")){
			chiffre1 = chiffre1 - 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}          
		if(operateur.equals("*")){
			chiffre1 = chiffre1 *
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}     
		if(operateur.equals("/")){
			try{
				chiffre1 = chiffre1 / 
						Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre1));
			} catch(ArithmeticException e) {
				ecran.setText("0");
			}
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur un bouton
	 * 
	 * @author Loris
	 *
	 */
	private class ChiffreListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){

			//On affiche le chiffre additionnel dans le label
			String str = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur le bouton egal
	 * 
	 * @author Loris
	 *
	 */
	private class EgalListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent arg0){
			calcul();
			update = true;
			clicOperateur = false;
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur le bouton plus
	 * 
	 * @author Loris
	 *
	 */

	private class PlusListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur le bouton moins
	 * 
	 * @author Loris
	 *
	 */
	private class MoinsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur le bouton multiplier
	 * 
	 * @author Loris
	 *
	 */
	private class MultiListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur le bouton diviser
	 * 
	 * @author Loris
	 *
	 */
	private class DivListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}

	/**
	 * 
	 * ActionListener qui se déclenche quand on clique sur le bouton remise à zéro
	 * 
	 * @author Loris
	 *
	 */
	private class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			clicOperateur = false;
			update = true;
			chiffre1 = 0;
			operateur = "";
			ecran.setText("");
		}
	}

	//getter/setter
	public void setEcran(JLabel ecran) {
		this.ecran = ecran;
	}


	public void setChiffre1(double chiffre1) {
		this.chiffre1 = chiffre1;
	}


	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public JLabel getEcran() {
		return ecran;
	}


}