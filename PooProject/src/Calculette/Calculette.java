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
import Panels.PanelCalculette;

public class Calculette extends JPanel
{

	PanelCalculette panelcalculette = new PanelCalculette("Calculatrice", "Calculatrice");
	IconBase point = new IconBase("images/icones/points.png", 50,50);



	JPanel center = new JPanel();
	JPanel bas = new JPanel();

	//Cr�ation du panel sur le click
	JPanel panelCop = new JPanel();



	//Tableau qui stocke les �l�ments de la calculatrice
	String [] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=",
			"C", "+", "-", "*", "/"};


	//bouton
	JButton [] tab_button = new JButton[tab_string.length];

	//JLabel ecran ou les chiffres seront not�s
	private JLabel ecran = new JLabel();

	//variable qui va permettre de faire les calcules
	private double chiffre1;

	private String operateur;

	//choisir la dimension des boutons
	private Dimension dim = new Dimension(60, 60);
	private Dimension dim2 = new Dimension(90, 90);

	private boolean clicOperateur = false, update = false;

	public Calculette()
	{

		//choisir la dimension du panel de base
		this.setPreferredSize(new Dimension(480, 40));
		this.setLayout(new BorderLayout());

		//on rajoute le panel par defaut des apps dans le north du panel de base
		this.add(panelcalculette, BorderLayout.NORTH);

		

		//on met le layout du centre pour l'ordre des panels
		center.setLayout(new BorderLayout());
		center.setBackground(Color.RED);

		//panel bas 
		bas.setLayout(new FlowLayout());
		bas.setSize(new Dimension(480, 40));
		bas.add(point);
		//mets tous les composants dans le panel center
		Composant();

		

		this.add(center, BorderLayout.CENTER);
		this.add(bas, BorderLayout.SOUTH);

	
		
		
	}


	private void Composant() 
	{
		//police des boutons
		Font police2 = new Font("Arial", Font.BOLD, 50);

		//On d�finit la police d'�criture � utiliser
		Font police = new Font("Arial", Font.BOLD, 40);
		ecran = new JLabel("0");
		ecran.setFont(police);
		//On aligne les informations � droite dans le JLabel
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
		//afin de cr�er nos boutons avec la couleur de fond chang�
		//ainsi que la police de caract�re

		for(int i = 0; i < tab_string.length; i++){
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dim);
			tab_button[i].setBackground(Color.darkGray);
			tab_button[i].setFont(police2);
			switch(i){

			//Pour chaque �l�ment situ� � la fin du tableau
			//et qui n'est pas un chiffre
			//on d�finit le comportement � avoir gr�ce � un listener
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
				//Par d�faut, ce sont les premiers �l�ments du tableau
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

	//M�thode permettant d'effectuer un calcul selon l'op�rateur s�lectionn�
	private void calcul(){
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

	
	//Listener utilis� pour les chiffres
	//Permet de stocker les chiffres et de les afficher
	class ChiffreListener implements ActionListener {
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

	//Listener affect� au bouton =
	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			calcul();
			update = true;
			clicOperateur = false;
		}
	}

	//Listener affect� au bouton +
	class PlusListener implements ActionListener {
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

	//Listener affect� au bouton -
	class MoinsListener implements ActionListener {
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

	//Listener affect� au bouton *
	class MultiListener implements ActionListener {
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

	//Listener affect� au bouton /
	class DivListener implements ActionListener {
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

	//Listener affect� au bouton de remise � z�ro
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			clicOperateur = false;
			update = true;
			chiffre1 = 0;
			operateur = "";
			ecran.setText("");
		}
	}

	
}