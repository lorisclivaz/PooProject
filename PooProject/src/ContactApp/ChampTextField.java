/*
* Author : Vivian Bridy
* Date creation : 1 mai 2018
*/
package ContactApp;

import java.awt.Font;

import javax.swing.JTextField;

public class ChampTextField extends JTextField{
	
	Font globalFontH2 = new Font("2.TimesRoman ",Font.BOLD,20);
	
	public ChampTextField(String text) {
		// TODO Auto-generated constructor stub
		this.setText(text);
		this.setFont(globalFontH2);
	}
}
