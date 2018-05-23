/*
* Author : Vivian Bridy & Loris Clivaz
* Date creation : 22 mai 2018
*/
package JUnit;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Test;

import Calculette.Calculette;

/**
 * Classe test JUnit pour la classe Calculette
 * 
 * @author Vivian
 *
 */
public class CalculetteTest {

	@Test
	/**
	 * Test de la méthode calcul
	 * 
	 * @author Vivian
	 */
	public void testCalcul() {
		//On initialise une calculette
		Calculette c = new Calculette();
		 
		/*
		 **********************************
		 ********ADDITION******************
		 **********************************
		*/ 
		 
		//On met le premier chiffre à 5.0
		JLabel ecran = new JLabel();
		ecran.setText("5.0");
		c.setEcran(ecran);
		
		//On met le deuxième à 10.0
		double chiffre1 = 10.0;
		c.setChiffre1(chiffre1);
		
		//On met l'opérateur à addition
		String operateur = "+";
		c.setOperateur(operateur);
		
		c.calcul();
		
		if(!c.getEcran().getText().equals("15.0"))
			fail("L'addition est fausse");
		
		/*
		 **********************************
		 ********SOUSTRACTION**************
		 **********************************
		*/ 
		
		//On met le premier chiffre à 5.0
		ecran.setText("5.0");
		c.setEcran(ecran);
		
		//On met le deuxième à 10.0
		chiffre1 = 10.0;
		c.setChiffre1(chiffre1);
		
		//On met l'opérateur à addition
		operateur = "-";
		c.setOperateur(operateur);
		
		c.calcul();
		
		if(!c.getEcran().getText().equals("5.0"))
			fail("La soustraction est fausse  ecran  vaut "+c.getEcran().getText());
		
		/*
		 **********************************
		 ********MULTIPLICATION************
		 **********************************
		*/ 
		
		//On met le premier chiffre à 5.0
		ecran.setText("5.0");
		c.setEcran(ecran);
		
		//On met le deuxième à 10.0
		chiffre1 = 10.0;
		c.setChiffre1(chiffre1);
		
		//On met l'opérateur à addition
		operateur = "*";
		c.setOperateur(operateur);
		
		c.calcul();
		
		if(!c.getEcran().getText().equals("50.0"))
			fail("La multiplication est fausse  ecran  vaut "+c.getEcran().getText());
		
		/*
		 **********************************
		 ********DIVISION******************
		 **********************************
		*/ 
		
		//On met le premier chiffre à 5.0
		ecran.setText("5.0");
		c.setEcran(ecran);
		
		//On met le deuxième à 10.0
		chiffre1 = 10.0;
		c.setChiffre1(chiffre1);
		
		//On met l'opérateur à addition
		operateur = "/";
		c.setOperateur(operateur);
		
		c.calcul();
		
		if(!c.getEcran().getText().equals("2.0"))
			fail("La division est fausse  ecran  vaut "+c.getEcran().getText());
		}
}
