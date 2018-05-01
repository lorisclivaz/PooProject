/*
* Author : Vivian Bridy
* Date creation : 1 mai 2018
*/
package ContactApp;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class ChampLabel extends JLabel {
	
	Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
	
	public ChampLabel(String nom,String type) {
		// TODO Auto-generated constructor stubs
		super(nom);
		//Design des champs
		this.setPreferredSize(new Dimension(200,30));
		this.setFont(globalFontH2);
	
	}
}
