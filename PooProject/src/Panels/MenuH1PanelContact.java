/*
 * Author : Vivian Bridy & Loris Clivaz
 * Date creation : 30 avr. 2018
 */
package Panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import Images.IconBase;

/**
 * Classe responsable de l'affichage du menu en haut de l'application contact
 * 
 * @author Vivian
 *
 */
public class MenuH1PanelContact extends PanelSettings{
	
	//On instancie les icones pour les ajouters sur le panel
	IconBase create = new IconBase("images/icones/plus.png",40,40);
	IconBase previous = new IconBase("images/icones/left-arrow.png",40,40);
	IconBase vide = new IconBase("images/icones/left-arrow.png",40,40);
	
	//Pour faire le tri des panels
	CardLayout cardlayout = new CardLayout();
	JPanel triPanel = new JPanel(cardlayout);

	/**
	 * Constructeur de la classe MenuH1PanelContact
	 * 
	 * @param titre : le titre de la page affichée
	 * @param nomClass : le nom de la classe précédente
	 * @author Vivian
	 */
	
	public MenuH1PanelContact(String titre, String nomClass)
	// TODO Auto-generated constructor stube
	{
		super(titre,nomClass);

		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,8)); 	//61 est la valeur max
		if(nomClass.equals("ContactPanel")) {
			this.add(vide, BorderLayout.WEST);
		}else {
			this.add(previous, BorderLayout.WEST);
		}

		//On met le plus à droite
		if(nomClass.equals("NewContact"))
			this.remove(create);
		else
			this.add(create, BorderLayout.EAST);
		
		//On met un listener sur le bouton
		create.addActionListener(new ClickCreate());
		previous.addActionListener(new ClickPrevious());

	}
	
	
	

	/**
	 * ActionListener qui se déclenche lors du click sur le bouton "précédent"
	 * 
	 * @author Vivian
	 *
	 */
	
	class ClickPrevious implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			cardlayout.show(triPanel, "scroll");
			
		}
		
	}

	//quand on clique sur le bouton create
	/**
	 * ActionListener qui se déclenche lors du clique sur le bouton create
	 * 
	 * @author Vivian
	 *
	 */
	class ClickCreate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			cardlayout.show(triPanel, "newcontact");

			System.out.println("create cliqué");
		}
	}
	
	public void setCardLayout(CardLayout cardlayout, JPanel triPanel) {
		this.cardlayout = cardlayout;
		this.triPanel = triPanel;
	}

}
